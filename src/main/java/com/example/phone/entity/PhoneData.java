package com.example.phone.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * Entity class to persist or retrieve data from H2 in-memory database
 * This can be possibly reused for other databases by defining appropriate
 * table and column annotation name property
 */
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