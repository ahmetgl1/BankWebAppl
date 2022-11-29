package com.ahmet.bankwebapp.BankWebApplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateCustomerRequest extends BaseCustomerRequest {

  //  @NotEmpty(message = "Id must not null !")
    private Long id;

    //Date Formatına çevirilecek !
    @NotEmpty(message = "Date must not null !")
    private String dateOfBirth;

}
