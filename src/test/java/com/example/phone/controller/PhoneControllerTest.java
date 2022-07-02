package com.example.phone.controller;

import com.example.phone.repository.PhoneDataRepository;
import com.example.phone.entity.PhoneData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PhoneControllerTest {

    @InjectMocks
    private PhoneController phoneController;

    @Mock
    private PhoneDataRepository phoneDataRepository;

    @Test
    void getAll() {
        List<PhoneData> phonesFromDb = Collections.singletonList(new PhoneData());
        Mockito.when(phoneDataRepository.findAll()).thenReturn(Collections.singletonList(new PhoneData()));
        List<PhoneData> phonesFromApi = phoneController.getAll();
        Assertions.assertEquals(phonesFromDb, phonesFromApi);

    }
}