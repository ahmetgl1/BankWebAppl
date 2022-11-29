package com.ahmet.bankwebapp.BankWebApplication.Controllers;


import com.ahmet.bankwebapp.BankWebApplication.Dto.CreateCustomerRequest;
import com.ahmet.bankwebapp.BankWebApplication.Dto.CustomerDto;
import com.ahmet.bankwebapp.BankWebApplication.Dto.UpdateCustomerRequest;
import com.ahmet.bankwebapp.BankWebApplication.Services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


   @PostMapping("/create")
   public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){

       return ResponseEntity.ok(customerService.createCustomer(createCustomerRequest));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> customerGetById(@PathVariable Long id){

        return ResponseEntity.ok(customerService.customerGetById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> customerGetAll(){

        return ResponseEntity.ok(customerService.customerGetAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){

       customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomerById(@PathVariable Long id , @RequestBody UpdateCustomerRequest updateCustomerRequest){

        return ResponseEntity.ok(customerService.updateCustomer(id,updateCustomerRequest));
    }



}
