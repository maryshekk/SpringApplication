package com.example.springapp.exception;

public class NoteAlreadyExistsException extends Exception {
    public NoteAlreadyExistsException(String msg)
    {
        super(msg);
    }
}
