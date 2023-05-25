package com.fmi.java.web.games_shop.exception;

public class EntityExistsException extends RuntimeException{
    public EntityExistsException(final String message) {
        super(message);
    }
}
