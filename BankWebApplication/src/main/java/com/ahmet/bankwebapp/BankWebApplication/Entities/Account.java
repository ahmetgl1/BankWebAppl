package com.ahmet.bankwebapp.BankWebApplication.Entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode

@Entity
public class Account {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "org.hibernate.id.UUIDGenerator")

    private String id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "account_balance")
    private Double balance;
    private City city;
    @Column(name = "account_currency")
    private Currency currency;
}
