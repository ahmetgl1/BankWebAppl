package com.ahmet.bankwebapp.BankWebApplication.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyTransferRequest {

    private String fromToId;
    private String senderToId;
    private Double amount;

}
