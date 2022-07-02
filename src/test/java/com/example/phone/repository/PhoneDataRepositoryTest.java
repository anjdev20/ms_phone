package com.example.phone.repository;

import com.example.phone.entity.PhoneData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@Transactional
public class PhoneDataRepositoryTest {

    @Autowired
    private PhoneDataRepository phoneDataRepository;


    @Test
    void testFindByCustomerId() {
        PhoneData phoneData = getPhoneData();
        phoneDataRepository.save(phoneData);
        //List<PhoneNumber> actualResult = phoneDataRepository.findByCustomerId("cus1");
      //  Assertions.assertEquals(phoneData, actualResult);
    }

    private PhoneData getPhoneData() {
        PhoneData phoneData = new PhoneData();
        phoneData.setId(1L);
        phoneData.setCustomerId("cus1");
        phoneData.setNumber("+61411111111");
        phoneData.setStatus("Active");
        return phoneData;
    }
}
