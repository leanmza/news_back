package com.lean.news.exception;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound(String message){
        super(message);
    }
}
