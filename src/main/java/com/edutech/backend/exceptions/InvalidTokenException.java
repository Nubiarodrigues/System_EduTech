package com.edutech.backend.exceptions;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(String msg){
        super(msg);
    }
}
