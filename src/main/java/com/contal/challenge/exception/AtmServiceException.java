package com.contal.challenge.exception;

public class AtmServiceException extends RuntimeException {

    public AtmServiceException(String message){
        super(message);
    }

    public AtmServiceException(String message, Throwable throwable){
        super(message,throwable);
    }
}
