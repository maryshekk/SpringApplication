package com.example.springapp.exception;

public class ProjectDoesNotFoundException extends Exception{
    public ProjectDoesNotFoundException(String msg)
    {
        super(msg);
    }
}
