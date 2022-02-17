package com.blog.Blog.controller;

import com.blog.Blog.model.LikeInput;
import com.blog.Blog.model.Message;
import com.blog.Blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/like")
@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping
    public Message likeMessageByUsername (@RequestBody LikeInput likeInput) {
        return likeService.likeMessage(likeInput);
    }
}
