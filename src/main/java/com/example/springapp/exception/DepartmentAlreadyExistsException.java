package com.example.springapp.exception;

public class DepartmentAlreadyExistsException extends Exception
{
    public DepartmentAlreadyExistsException(String msg)
    {
        super(msg);
    }
}
