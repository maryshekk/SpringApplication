package com.example.springapp.exception;

public class EmployeeDoesNotFoundException extends Exception
{
    public EmployeeDoesNotFoundException(String message)
    {
        super(message);
    }
}
