package com.ahmet.bankwebapp.BankWebApplication.Dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode

public class CustomerDto {

    private Long id;
    private String name;
    private String surName;
    private String dateOfBirth;
    private CityDto city;


}
