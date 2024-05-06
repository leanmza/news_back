package com.lean.news.exception;

public class UserNotFound extends RuntimeException{

    public UserNotFound (String message){
        super(message);
    }
}
