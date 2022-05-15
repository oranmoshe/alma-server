package com.roe.almaserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Already exist")
public class EntityAlreadyExistException extends RuntimeException{

    public EntityAlreadyExistException(String message) {
        super(message);
    }

    public EntityAlreadyExistException(String type, String id) {
        super("Resource: " + type + " identified by \"" + id +"\" is already exist");
    }

    public EntityAlreadyExistException(Class type, Long id) {
        this(type.getSimpleName(), id.toString());
    }

    public EntityAlreadyExistException(Class type, String id) {
        this(type.getSimpleName(), id);
    }
}
