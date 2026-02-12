package com.yousra.miawpaw.security.exceptions;

public class UserIsActiveException extends RuntimeException {
    public UserIsActiveException(Object email) {
        super("User with this email <" + email + "> is suspended, you can call the support");
    }
}
