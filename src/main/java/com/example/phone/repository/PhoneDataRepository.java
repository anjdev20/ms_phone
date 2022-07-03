package com.example.phone.repository;

import com.example.phone.entity.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
    @Modifying
    @Transactional
    @Query(value = "update PhoneData p set p.status = :status where  p.number= :number")
    int updateStatus(@Param(value = "number") String number, @Param(value = "status") String status);

    PhoneData findByNumber(String s);


}