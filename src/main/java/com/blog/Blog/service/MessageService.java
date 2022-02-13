package com.blog.Blog.service;

import com.blog.Blog.model.Message;
import com.blog.Blog.model.MessageInput;
import com.blog.Blog.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message getMessageByMessageId(UUID messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        return message.orElse(null);

    }

    public Message postNewMessage(MessageInput messageInput) {
        Message message = new Message (UUID.randomUUID(), messageInput.getContent(),
                messageInput.getTag(),
                0,
                messageInput.getUserId()
                );
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(){
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageByUserId(Long userId) {
        return messageRepository.findByUserId(userId);
    }
}
