package com.blog.Blog.model;

import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
public class Message {
    //UserId + Id + Content + Tag + LikeCount +
    @Id
    private UUID messageId;
    private String content;
    private String tag;
    private int likeCount;
    private String username;
    private LocalDateTime postDate;
    @ElementCollection(targetClass=String.class)
    private Set<String> likedUsers;


    public Message(UUID messageId, String content, String tag, String username) {
        this.messageId = messageId;
        this.content = content;
        this.tag = tag;
        this.username = username;
        this.postDate = LocalDateTime.now();
        this.likeCount = 0;
        this.likedUsers = new HashSet<>();
    }

    public Message(){

    }



    public int getLikeCount() {
        return likedUsers.size();
    }

    public String getUsername() {
        return username;
    }

    public boolean addLikedUser(String username) {
        if (likedUsers.contains(username)){
            return false;
        }
        likedUsers.add(username);
        return true;
    }
}
