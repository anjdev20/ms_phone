package com.example.phone.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class PhoneData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String customerId;

    @Column
    private String fullName;

    @Column
    private String number;

    @Column
    private String status;
}