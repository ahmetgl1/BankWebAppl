package com.ahmet.bankwebapp.BankWebApplication.Entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Address {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private City city;
    private String postCode;
    private String addressDetail;


}
