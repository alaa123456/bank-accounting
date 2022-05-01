package com.bank.exception;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String msg) {
        super(msg);
    }
}
