package com.example.springapp.exception;

public class WrongIdException extends Exception{
    public WrongIdException(String msg)
    {
        super(msg);
    }
}
