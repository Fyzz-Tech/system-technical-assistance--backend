package com.technical_assistance.project.exceptions;

import java.io.Serial;

public class ExternalServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ExternalServiceException(String message) {
        super(message);
    }
}
