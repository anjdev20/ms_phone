package com.example.phone.entity;


import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;


public interface PhoneNumber {

    String getNumber();

    String getStatus();
}
