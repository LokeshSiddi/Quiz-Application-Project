package com.lokesh.Quiz_Application_Project.exception;

import lombok.experimental.StandardException;

@StandardException
public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
