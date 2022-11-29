package com.ahmet.bankwebapp.BankWebApplication.Dto;

import com.ahmet.bankwebapp.BankWebApplication.Entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {


    public CustomerDto convert(Customer customer){

        CustomerDto customerDto = new CustomerDto();


        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setSurName(customer.getSurName());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setCity(CityDto.valueOf(customer.getCity().name()));

        return customerDto;



    }

}
