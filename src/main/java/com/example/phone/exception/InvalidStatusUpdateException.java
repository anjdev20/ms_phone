package com.example.phone.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Custom Exception class for invalid status sent in request for Patch
 */
@Getter
@Setter
@Slf4j
public class InvalidStatusUpdateException extends RuntimeException {
    private String errorMessage;

    public InvalidStatusUpdateException(String message) {
        super(message);
    }
}
