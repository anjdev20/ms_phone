package com.example.phone.service;

import com.example.phone.entity.PhoneData;
import com.example.phone.entity.PhoneDataResponse;
import com.example.phone.exception.InvalidStatusUpdateException;
import com.example.phone.exception.NoDataFoundException;
import com.example.phone.repository.PhoneDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @InjectMocks
    private PhoneServiceImpl phoneService;

    @Mock
    private PhoneDataRepository phoneDataRepository;

    @Test
    void testGetPhoneForOneUser() {
        String cusId = "cus1";
        PhoneData phoneData = new PhoneData();
        phoneData.setCustomerId(cusId);
        List<PhoneData> phonesFromDb = Collections.singletonList(phoneData);
        Mockito.when(phoneDataRepository.findAll(any(Example.class))).thenReturn(phonesFromDb);
        List<PhoneDataResponse> phonesFromApi = phoneService.getPhoneForUser(cusId);
        Assertions.assertEquals(phonesFromDb.get(0).getCustomerId(), phonesFromApi.get(0).getCustomerId());
        Assertions.assertEquals(phonesFromDb.size(), phonesFromApi.size());
    }

    @Test
    void testNoCustomerIdFound() {
        String cusId = "cus";
        List<PhoneData> phonesFromDb = new ArrayList<PhoneData>();
        Mockito.when(phoneDataRepository.findAll(any(Example.class))).thenReturn(phonesFromDb);
        NoDataFoundException exception = Assertions.assertThrows(NoDataFoundException.class, () -> {
            phoneService.getPhoneForUser(cusId);
        });

        String expectedMessage = "Customer ID not found";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetPhoneForAllUsers() {
        PhoneData phoneData = new PhoneData();
        List<PhoneData> phonesFromDb = Collections.singletonList(phoneData);
        Mockito.when(phoneDataRepository.findAll(any(Example.class))).thenReturn(phonesFromDb);
        List<PhoneDataResponse> phonesFromApi = phoneService.getPhoneForUser(null);
        Assertions.assertEquals(phonesFromDb.size(), phonesFromApi.size());
        Assertions.assertEquals(phonesFromDb.size(), phonesFromApi.size());
    }

    @Test
    void testActivateValid() {
        String number = "+61411111111";
        String status = "Active";
        PhoneData phoneData = getPhoneData();
        Mockito.when(phoneDataRepository.findByNumber(any())).thenReturn(phoneData);
        ResponseEntity actual = phoneService.activatePhone(number, status);
        ResponseEntity expected = new ResponseEntity("Phone status updated", HttpStatus.OK);
        verify(phoneDataRepository, times(1)).updateStatus(any(), any());
    }

    @Test
    void testActivateInvalidNumber() {
        String number = "+1111";
        String status = "Active";

        Mockito.when(phoneDataRepository.findByNumber(any())).thenReturn(null);
        NoDataFoundException exception = Assertions.assertThrows(NoDataFoundException.class, () -> {
            phoneService.activatePhone(number, status);
        });

        String expectedMessage = "Phone number does not exist";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
        verify(phoneDataRepository, never()).updateStatus(any(), any());
    }

    @Test
    void testActivateInvalidStatus() {
        String number = "+61411111111";
        String status = "Activ";
        PhoneData phoneData = getPhoneData();
        Mockito.when(phoneDataRepository.findByNumber(any())).thenReturn(phoneData);
        InvalidStatusUpdateException exception = Assertions.assertThrows(InvalidStatusUpdateException.class, () -> {
            phoneService.activatePhone(number, status);
        });
        String expectedMessage = "Invalid status update requested";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
        verify(phoneDataRepository, never()).updateStatus(any(), any());
    }

    protected PhoneData getPhoneData() {
        PhoneData phoneData = new PhoneData();
        phoneData.setId(1L);
        phoneData.setCustomerId("cus1");
        phoneData.setFullName("Paul Stake");
        phoneData.setNumber("+61411111111");
        phoneData.setStatus("Active");

        return phoneData;
    }

}