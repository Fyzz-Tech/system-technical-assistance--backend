package com.technical_assistance.project.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message){
        super("Resource not found: " + message);
    }
}
