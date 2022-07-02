package com.example.phone.repository;

import com.example.phone.entity.PhoneData;
import com.example.phone.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
    List<PhoneNumber> findByCustomerId(String customerId);
    

    List<PhoneNumber> findBy();

    @Modifying
    @Transactional
    @Query(value = "update PhoneData p set p.status = :status where  p.number= :number")
    void updateStatus (@Param(value="status") String status, @Param(value="number") String number);

    PhoneData findByNumber(String s);
}