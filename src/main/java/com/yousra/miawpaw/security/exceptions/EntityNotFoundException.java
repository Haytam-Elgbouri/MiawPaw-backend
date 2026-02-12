package com.yousra.miawpaw.security.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Object id) {
        super(entity + " with the id " + id + " not found");
    }
}
