package com.example.phone.service;

import com.example.phone.entity.PhoneData;
import com.example.phone.entity.PhoneDataResponse;
import com.example.phone.exception.InvalidStatusUpdateException;
import com.example.phone.exception.NoDataFoundException;
import com.example.phone.repository.PhoneDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service interface implementation for any logic required before or after retrieval from repository
 */
@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    private static final String ACTIVE = "Active";
    private static final String INACTIVE = "Inactive";
    //Autowiring phoneData repo
    @Autowired
    private PhoneDataRepository phoneDataRepository;

    /**
     * Method to retrieve all phone number details or details of a specific
     * customer using optional query param for customerId
     */
    @Override
    public List<PhoneDataResponse> getPhoneForUser(String cusId) {
        log.debug("Going to retrieve phone number details");
        PhoneData phoneData = new PhoneData();
        phoneData.setCustomerId(cusId);
        if (cusId == null)
            log.info("Retrieving all details");
        else
            log.info("Retrieving all phone numbers for customer: " + cusId);
        /**Using a separate response object to send API response to make it more scalable*/
        List<PhoneData> phoneDataList = phoneDataRepository.findAll(Example.of(phoneData));
        if (phoneDataList.size() == 0) {
            log.error("Customer ID not found");
            throw new NoDataFoundException("Customer ID not found");
        }
        List<PhoneDataResponse> phoneDataResponse = new ArrayList<>(phoneDataList.size());
        log.info("Creating a new response object");
        for (PhoneData phone : phoneDataList) {
            phoneDataResponse.add(new PhoneDataResponse(phone.getId(), phone.getCustomerId(),
                    phone.getFullName(), phone.getNumber(), phone.getStatus()));
        }
        return phoneDataResponse;
    }

    /**
     * Method to update customer using optional query param for customerId
     * When custId is not passed in, all phone number details are retrieved,
     * else only a specific customer phone details are retrieved
     */
    @Override
    public ResponseEntity activatePhone(String number, String status) {
        log.debug("Going to update status of phone number");
        //Check if phone number exists and status to be updated is valid
        PhoneData phoneData = phoneDataRepository.findByNumber(number);
        if (phoneData == null) {
            /**If phone number does not exist*/
            log.error("Phone number does not exist");
            throw new NoDataFoundException("Phone number does not exist");
        }
        if (phoneData != null) {
            /**If status received in path param is incorrect, respond with an error*/
            if (!((status.equals(ACTIVE) || status.equals(INACTIVE)))) {
                log.error("Invalid status - valid statuses are Active/InActive");
                throw new InvalidStatusUpdateException("Invalid status update requested");
            }
        }
        phoneDataRepository.updateStatus(status, number);
        log.info("Status of phone number " + number + " updated to " + status);
        //return success message
        return new ResponseEntity("Phone status updated", HttpStatus.OK);
    }
}
