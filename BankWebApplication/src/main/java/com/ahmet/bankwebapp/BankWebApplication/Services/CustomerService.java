package com.ahmet.bankwebapp.BankWebApplication.Services;

import com.ahmet.bankwebapp.BankWebApplication.Dto.CreateCustomerRequest;
import com.ahmet.bankwebapp.BankWebApplication.Dto.CustomerDto;
import com.ahmet.bankwebapp.BankWebApplication.Dto.CustomerDtoConverter;
import com.ahmet.bankwebapp.BankWebApplication.Dto.UpdateCustomerRequest;
import com.ahmet.bankwebapp.BankWebApplication.Entities.City;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Customer;
import com.ahmet.bankwebapp.BankWebApplication.Exceptions.CustomerNotFoundException;
import com.ahmet.bankwebapp.BankWebApplication.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService   {
  private final CustomerRepository customerRepository;
  private final CustomerDtoConverter customerDtoConverter;


  public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
    this.customerRepository = customerRepository;
    this.customerDtoConverter = customerDtoConverter;
  }

   public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest){

     Customer customer = new Customer();

     customer.setId(createCustomerRequest.getId());
     customer.setName(createCustomerRequest.getName());
     customer.setSurName(createCustomerRequest.getSurName());
     customer.setDateOfBirth(createCustomerRequest.getDateOfBirth());
     customer.setCity(City.valueOf(createCustomerRequest.getCity().name()));

     customerRepository.save(customer);

     return customerDtoConverter.convert(customer);

   }

       public CustomerDto customerGetById(Long id){

        Optional<Customer> customerId = customerRepository.findById(id);


           return customerId.map(customerDtoConverter::convert)
                   .orElseThrow(() -> new CustomerNotFoundException("Custome  r Not Found !"));
       }

   public List<CustomerDto> customerGetAll(){

    List<Customer> customerList = customerRepository.findAll();

    List<CustomerDto> customerDtoList =   new ArrayList<>();

       for(Customer customer: customerList) {
           customerDtoList.add(customerDtoConverter.convert(customer));
       }

       return customerDtoList;
   }


    public void deleteCustomer(Long id) {

      Optional<Customer> optionalCustomer = customerRepository.findById(id);
      optionalCustomer.map(customer -> customerRepository.findById(id))
              .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found !"));


    }

    public CustomerDto updateCustomer(Long id , UpdateCustomerRequest updateCustomerRequest){

      Optional<Customer> customerOptional = customerRepository.findById(id);
        customerOptional.ifPresent(customer -> {


            customer.setName(updateCustomerRequest.getName());
            customer.setSurName(updateCustomerRequest.getSurName());
            customer.setCity(City.valueOf(updateCustomerRequest.getCity().name()));

        customerRepository.save(customer);

        });

        return customerOptional.map(customerDtoConverter::convert)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found !"));
    }
    public Customer getCustomerById(Long id){

      //exception fırlatılacak !
     return customerRepository.findById(id)
             .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found !"));

    }

}
