package com.ahmet.bankwebapp.BankWebApplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateCustomerRequest extends BaseCustomerRequest {


    private String name;

    private String surName;

    private CityDto city;

}
