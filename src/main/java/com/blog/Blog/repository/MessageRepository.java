package com.blog.Blog.repository;

import com.blog.Blog.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<Message, UUID> {
    public Message findByUsername(String username);


    public Message findFirstByUsernameOrderByPostDateDesc(String username);
}
