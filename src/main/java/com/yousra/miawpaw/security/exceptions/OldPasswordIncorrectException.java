package com.yousra.miawpaw.security.exceptions;

public class OldPasswordIncorrectException extends RuntimeException {
    public OldPasswordIncorrectException(String message) {
        super(message);
    }
}
