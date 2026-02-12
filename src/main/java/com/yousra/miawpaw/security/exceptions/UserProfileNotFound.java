package com.yousra.miawpaw.security.exceptions;

public class UserProfileNotFound extends RuntimeException {
    public UserProfileNotFound(String message) {
        super(message);
    }
}
