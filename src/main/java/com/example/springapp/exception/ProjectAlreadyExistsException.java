package com.example.springapp.exception;

public class ProjectAlreadyExistsException extends Exception{
    public ProjectAlreadyExistsException(String msg)
    {
        super(msg);
    }
}
