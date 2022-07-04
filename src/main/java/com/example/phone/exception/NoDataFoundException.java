package com.example.phone.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Custom Exception class for resource not found exception
 */
@Getter
@Setter
@Slf4j
public class NoDataFoundException extends RuntimeException {
    private String errorMessage;
    public NoDataFoundException(String message) {
        super(message);
    }
}
