package com.blog.Blog.model;

import lombok.Getter;

@Getter
public class LikeInput {

    private String username;
    private String messageId;

    public LikeInput(String username, String messageId) {
        this.username = username;
        this.messageId = messageId;
    }
}
