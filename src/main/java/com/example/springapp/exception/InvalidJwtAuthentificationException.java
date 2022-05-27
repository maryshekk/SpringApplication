package com.example.springapp.exception;

public class InvalidJwtAuthentificationException extends Exception {
    public InvalidJwtAuthentificationException(String msg) {
        super(msg);
    }
}
