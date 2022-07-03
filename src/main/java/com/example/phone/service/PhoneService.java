package com.example.phone.service;

import com.example.phone.entity.PhoneData;
import com.example.phone.entity.PhoneDataResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Service interface to implement logic required before or after retrieval from repository
 */
public interface PhoneService {
    List<PhoneDataResponse> getPhoneForUser(String userId);

    ResponseEntity activatePhone(String number, String status);
}
