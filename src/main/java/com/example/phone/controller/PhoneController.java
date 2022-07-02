package com.example.phone.controller;

import com.example.phone.repository.PhoneDataRepository;
import com.example.phone.entity.PhoneData;
import com.example.phone.entity.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PhoneController {

    @Autowired
    PhoneDataRepository phoneDataRepository;

    @GetMapping(path = "/contactmgmt/details")
    public @ResponseBody List<PhoneData> getAll() {
        return phoneDataRepository.findAll();
    }

    @GetMapping(path = "/contactmgmt/phoneNumbers")
    public @ResponseBody List<PhoneNumber> getAllPhone(@RequestParam(value = "userId", required = false) String userId) {
       if(userId == null)
           return phoneDataRepository.findBy();
       else
           return phoneDataRepository.findByCustomerId(userId);
    }

    @PatchMapping (path = "/contactmgmt/updateStatus/{number}/{status}")
    public ResponseEntity activatePhone(@PathVariable("number") String number, @PathVariable ("status") String status) {

       PhoneData phoneData=  phoneDataRepository.findByNumber(number);
       if(phoneData != null) {
           if (status.equals("Active") || status.equals("Inactive")) {

               log.info("Updating phone number status");
               phoneDataRepository.updateStatus(status, number);
               return new ResponseEntity("Phone status updated", HttpStatus.OK);
           }
           else
               return new ResponseEntity("Invalid status - valid statuses are Active/Inactive", HttpStatus.BAD_REQUEST);

       }
            return new ResponseEntity("Invalid phone number", HttpStatus.NOT_FOUND);
    }

}
