package com.contal.challenge.exception;

public class AmountNotEnoughException extends RuntimeException{
    public AmountNotEnoughException(String message){
        super(message);
    }
}
