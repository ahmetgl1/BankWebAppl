package com.ahmet.bankwebapp.BankWebApplication.Dto;

import com.ahmet.bankwebapp.BankWebApplication.Entities.City;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Currency;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Builder()

public class AccountDto {

    private String id;
    private long customerId;
    private Double balance;
    private City city;
    private Currency currency;

}
