package com.example.onboard.infrastructure.exception;

public class MadinaAppException extends RuntimeException {

    public MadinaAppException(String message) {
        super(message);
    }
    public MadinaAppException(String message, Throwable cause) {
        super(message, cause);
    }
}