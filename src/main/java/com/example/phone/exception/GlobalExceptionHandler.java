package com.example.phone.exception;

import com.example.phone.entity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global Exception handler to handle exceptions across the application
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value
            = NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse
    handleNoDataFoundException(NoDataFoundException ex) {
        log.error("Creating error response 404");
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }


    @ExceptionHandler(value
            = InvalidStatusUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse
    handleInvalidStatusUpdateException(Exception ex) {
        log.error("Creating error response 400");
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
