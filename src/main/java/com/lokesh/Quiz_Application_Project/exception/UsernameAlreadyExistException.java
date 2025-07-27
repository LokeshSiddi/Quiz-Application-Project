package com.lokesh.Quiz_Application_Project.exception;

import lombok.experimental.StandardException;

@StandardException
public class UsernameAlreadyExistException extends RuntimeException{
    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
