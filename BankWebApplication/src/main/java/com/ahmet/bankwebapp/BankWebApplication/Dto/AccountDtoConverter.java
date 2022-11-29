package com.ahmet.bankwebapp.BankWebApplication.Dto;

import com.ahmet.bankwebapp.BankWebApplication.Entities.Account;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Currency;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

    public AccountDto convert(Account account){


        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setCustomerId(account.getCustomerId());
        accountDto.setBalance(account.getBalance());
        accountDto.setCurrency(Currency.valueOf(account.getCurrency().name()));
        accountDto.setCity(account.getCity());

        return accountDto;


    }




}
