package com.edutech.backend.exceptions;

public class ErrorGeneratedTokenException extends RuntimeException{

    public ErrorGeneratedTokenException(String msg){
        super(msg);
    }
}
