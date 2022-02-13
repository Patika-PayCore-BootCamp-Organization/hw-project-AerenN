package com.blog.Blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class Message {
    //UserId + Id + Content + Tag + LikeCount +
    @Id
    private UUID messageId;
    private String content;
    private String tag;
    private int likeCount;
    private Long userId;


    public Message() {
    }
}
