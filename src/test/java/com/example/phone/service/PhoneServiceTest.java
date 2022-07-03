package com.example.phone.service;

import com.example.phone.entity.PhoneData;
import com.example.phone.entity.PhoneDataResponse;
import com.example.phone.repository.PhoneDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

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
    void testGetPhoneForAllUsers() {
        PhoneData phoneData = new PhoneData();
        List<PhoneData> phonesFromDb = Collections.singletonList(phoneData);
        Mockito.when(phoneDataRepository.findAll(any(Example.class))).thenReturn(phonesFromDb);
        List<PhoneDataResponse> phonesFromApi = phoneService.getPhoneForUser(null);
        Assertions.assertEquals(phonesFromDb.size(), phonesFromApi.size());
        Assertions.assertEquals(phonesFromDb.size(), phonesFromApi.size());
    }

}