package com.roe.almaserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find resource")
public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String type, String id) {
        super("Could not find resource: " + type + " identified by \"" + id+"\"");
    }

    public EntityNotFoundException(Class type, Long id) {
        this(type.getSimpleName(), id.toString());
    }

    public EntityNotFoundException(Class type, String id) {
        this(type.getSimpleName(), id);
    }
}
