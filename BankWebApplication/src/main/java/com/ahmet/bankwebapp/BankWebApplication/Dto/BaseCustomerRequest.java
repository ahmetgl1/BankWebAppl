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

public class BaseCustomerRequest {

   @NotEmpty(message = "Name must not null !")
   @Size(min = 3 , message = "  name must be with minimum 3 characters ")
   private String name;

   @Size(min = 2 , message = "  name must be with minimum 2 characters ")
   private String surName;

   @NotNull(message = "City must not null !")
   private CityDto city;


}
