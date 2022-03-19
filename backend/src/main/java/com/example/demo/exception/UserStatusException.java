package com.example.demo.exception;

public class UserStatusException extends RuntimeException {
    public UserStatusException(String errorMessage){
        super(errorMessage);
    }
}
