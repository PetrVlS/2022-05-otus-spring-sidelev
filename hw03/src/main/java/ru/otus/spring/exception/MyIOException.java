package ru.otus.spring.exception;

public class MyIOException extends RuntimeException {

    public MyIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
