package com.example.springapp.exception;

public class EmployeeAlreadyExistsException extends Exception
{
    public EmployeeAlreadyExistsException(String message)
    {
        super(message);
    }

}
