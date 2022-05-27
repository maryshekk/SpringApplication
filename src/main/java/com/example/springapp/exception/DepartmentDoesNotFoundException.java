package com.example.springapp.exception;

public class DepartmentDoesNotFoundException extends Exception {
    public DepartmentDoesNotFoundException(String msg)
    {
        super(msg);
    }
}
