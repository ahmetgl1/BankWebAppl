package com.ahmet.bankwebapp.BankWebApplication.Entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode


@Entity
public class Customer {

    @Id
    @Column(name ="customer_id")
    private Long id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_surname")
    private String surName;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "customer_city")
    private City city;


    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "address_id" , referencedColumnName = "id" , nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "account_id" , referencedColumnName = "id" , nullable = false)
    private Account account;






}
