package com.blog.Blog.service;

import com.blog.Blog.config.RabbitMQConfig;
import com.blog.Blog.exception.UserNotValidException;
import com.blog.Blog.model.LikeInput;
import com.blog.Blog.model.Message;
import com.blog.Blog.model.User;
import com.blog.Blog.model.UserLikedMessageEvent;
import com.blog.Blog.repository.UserRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LikeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    /**
     * This method likes the message and sends {@link UserLikedMessageEvent} event to RabbitMQ.
     * Validate username
     * Get message from db
     * Update likeCount and likedUsers
     * Send new RabbitMQ event.
     * @param likeInput
     * @return {@link Message}
     */
    public Message likeMessage(LikeInput likeInput) {

        Optional<User> user = userRepository.findByUsername(likeInput.getUsername());
        if (user.isEmpty()) {
            throw new UserNotValidException("User doesn't exist?!");
        }
        Message message = messageService.getMessageByMessageId(UUID.fromString(likeInput.getMessageId()));
        boolean addedLike = message.addLikedUser(likeInput.getUsername());
        if (addedLike){
            return messageService.saveMessage(message);
        }
        sendUserLikedMessageEvent(likeInput, message.getTag());
        return message;
    }

    public void sendUserLikedMessageEvent(LikeInput likeInput, String messageTag){
        UserLikedMessageEvent userLikedMessageEvent = new UserLikedMessageEvent(likeInput.getUsername(), likeInput.getMessageId(), messageTag);
        template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, userLikedMessageEvent);
    }

}
