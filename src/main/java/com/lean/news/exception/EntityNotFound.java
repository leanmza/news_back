package com.lean.news.exception;

import javax.persistence.EntityNotFoundException;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound(String message){
        super(message);
    }
}
