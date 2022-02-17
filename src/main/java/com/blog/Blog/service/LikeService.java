package com.blog.Blog.service;

import com.blog.Blog.exception.UserNotValidException;
import com.blog.Blog.model.LikeInput;
import com.blog.Blog.model.Message;
import com.blog.Blog.model.User;
import com.blog.Blog.repository.UserRepository;
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

    public Message likeMessage(LikeInput likeInput) {
        //validate username
        //get message from db
        //update likecount + likedusers

        //TODO CREATE USERSERVICE AND USE USERREPOSITORY METHOD FROM THERE

        Optional<User> user = userRepository.findByUsername(likeInput.getUsername());
        if (user.isEmpty()) {
            throw new UserNotValidException("User doesn't exist?!");
        }
        Message message = messageService.getMessageByMessageId(UUID.fromString(likeInput.getMessageId()));
        boolean addedLike = message.addLikedUser(likeInput.getUsername());
        if (addedLike){
            return messageService.saveMessage(message);
        }
        return message;
    }
}
