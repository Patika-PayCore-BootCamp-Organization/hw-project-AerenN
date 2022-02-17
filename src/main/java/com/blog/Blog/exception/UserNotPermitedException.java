package com.blog.Blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Nein!")
public class UserNotPermitedException extends RuntimeException {

    public UserNotPermitedException(String message) {
        super(message);
    }
}
