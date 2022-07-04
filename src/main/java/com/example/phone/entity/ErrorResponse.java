package com.example.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Custom error response class to create readable API error response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ErrorResponse {
    private int errorCode;
    private String errorMessage;
}
