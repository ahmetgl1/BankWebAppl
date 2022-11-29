package com.ahmet.bankwebapp.BankWebApplication.Dto;

import com.ahmet.bankwebapp.BankWebApplication.Entities.City;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Currency;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateAccountRequest extends BaseAccountRequest {


    @NotEmpty(message = "Customer Id must not null !")
    private Long customerId;

    @Min(value = 0,  message = "balance must  not less than '0' !")
    private Double balance;

    @NotEmpty(message = "City can not be null !")
    private City city;

    @NotNull(message = "Currency can not be null !")
    private Currency currency;



}
