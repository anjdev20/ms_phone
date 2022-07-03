package com.example.phone.controller;

import com.example.phone.entity.PhoneDataResponse;
import com.example.phone.service.PhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class to map endpoints of the Phone microservice
 */
@RestController
@Slf4j
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    /**
     * GET request mapping to retrieve phone numbers in 2 ways
     * - as a whole
     * - of a specific customer (by the use of optional query param)
     */
    @GetMapping(path = "/contactmgmt/phone-details")
    public @ResponseBody ResponseEntity<List<PhoneDataResponse>> getAllPhone(@RequestParam(value = "customer-id", required = false) String custId) {
        List<PhoneDataResponse> phoneDataResponse = phoneService.getPhoneForUser(custId);
        return new ResponseEntity<>(phoneDataResponse, HttpStatus.OK);
    }

    /**
     * PATCH request mapping for partial update of row/resource
     */
    @PatchMapping(path = "/contactmgmt/phone-status/{number}/{status}")
    public @ResponseBody ResponseEntity activatePhone(@PathVariable("number") String number, @PathVariable("status") String status) {
        return (phoneService.activatePhone(number, status));
    }
}
