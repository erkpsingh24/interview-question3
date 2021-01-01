package com.example.demo.exception;

/**
 Exception thrown when an object is not found in the api call
 */
public class RecordNotFoundException extends Exception {

    public RecordNotFoundException(String msg) {
        super(msg);
    }
}
