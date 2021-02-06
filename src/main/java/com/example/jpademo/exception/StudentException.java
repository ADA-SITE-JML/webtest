package com.example.jpademo.exception;

public class StudentException extends Exception {
    Integer errorCode;

    public StudentException(Integer ec, String message) {
        super(message);
        errorCode = ec;
    }

    @Override
    public String getMessage() {
        return "Entity error ("+errorCode+") : " + super.getMessage();
    }
}
