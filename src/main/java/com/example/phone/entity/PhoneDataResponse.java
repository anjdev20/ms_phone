package com.example.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**Response object class to write API GET method responses into.
 *This will make the microservice more scalable in terms of
 * changing databases for example */
@Getter
@Setter
@AllArgsConstructor
public class PhoneDataResponse {

    private Long id;

    private String customerId;

    private String fullName;

    private String number;

    private String status;
}