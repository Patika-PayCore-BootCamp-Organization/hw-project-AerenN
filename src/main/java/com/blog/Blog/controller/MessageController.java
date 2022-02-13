package com.blog.Blog.controller;

import com.blog.Blog.model.Message;
import com.blog.Blog.model.MessageInput;
import com.blog.Blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(path = "/api/message")
@RestController
public class MessageController {
    //getMessageByUserId
    //updateMessage

    @Autowired
    private MessageService messageService;

    @GetMapping(path = "/getUserMessage")
    public Message getMessageByUserId(@RequestParam Long userId){
        return messageService.getMessageByUserId(userId);
    }

    //TODO :THROW EXCEPTION HERE
    @GetMapping(path = "/getMessage")
    public Message getMessageById(@RequestParam String messageId){
        return messageService.getMessageByMessageId(UUID.fromString(messageId));
    }

    @PostMapping(path = "/postMessage")
    public Message postNewMessage(@RequestBody MessageInput messageInput){
        return messageService.postNewMessage(messageInput);
    }

    @GetMapping(path = "/getAllMessages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }
}
