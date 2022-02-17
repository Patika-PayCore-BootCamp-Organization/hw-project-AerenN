package com.blog.Blog.service;

import com.blog.Blog.exception.UserNotPermitedException;
import com.blog.Blog.model.Message;
import com.blog.Blog.model.MessageInput;
import com.blog.Blog.repository.MessageRepository;
import com.blog.Blog.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message getLastMessage(String username){
        return messageRepository.findFirstByUsernameOrderByPostDateDesc(username);
    }



    public Message getMessageByMessageId(UUID messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        return message.orElse(null);

    }

    public Message postNewMessage(MessageInput messageInput) {
        Message message = new Message (UUID.randomUUID(), messageInput.getContent(),
                messageInput.getTag(),
                messageInput.getUsername()
                );
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(){
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageByUserId(String username) {
        return messageRepository.findByUsername(username);
    }

    public Message removeMessageByMessageId(UUID messageId){
        messageRepository.deleteById(messageId);
        return null;
    }

    public Message deleteMessage(UUID messageId){
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isPresent()){
            String username = message.get().getUsername();
            if (username.equals(getPrincipal().getUsername())) {
                messageRepository.deleteById(messageId);
            }else {
                throw new UserNotPermitedException("User is not allowed to Delete Message");
            }
        }

        return null;
    }

    // return username of the user
    private UserDetailsImpl getPrincipal(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) auth.getPrincipal();
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
