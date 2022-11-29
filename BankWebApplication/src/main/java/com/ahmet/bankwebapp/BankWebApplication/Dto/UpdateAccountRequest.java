package com.ahmet.bankwebapp.BankWebApplication.Dto;

import com.ahmet.bankwebapp.BankWebApplication.Entities.City;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateAccountRequest {

    private Double balance;
    private City city;
    private Currency currency;
}
