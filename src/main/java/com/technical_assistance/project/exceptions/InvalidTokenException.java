package com.technical_assistance.project.exceptions;

import java.io.Serial;

public class InvalidTokenException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidTokenException(String msg){
        super(msg);
    }
}

