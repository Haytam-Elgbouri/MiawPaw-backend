package com.yousra.miawpaw.security.exceptions;

public class UsernameNotExistException extends RuntimeException {
    public UsernameNotExistException(String message) {
        super(message);
    }
}
