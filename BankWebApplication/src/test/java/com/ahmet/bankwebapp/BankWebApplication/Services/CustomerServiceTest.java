
package com.ahmet.bankwebapp.BankWebApplication.Services;

import com.ahmet.bankwebapp.BankWebApplication.Dto.*;
import com.ahmet.bankwebapp.BankWebApplication.Entities.City;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Customer;
import com.ahmet.bankwebapp.BankWebApplication.Repositories.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;

public class CustomerServiceTest {
//
//    private CustomerService customerService;
//    private  CustomerRepository customerRepository;
//    private CustomerDtoConverter customerDtoConverter;
//
//    @Before
//    public void setUp() throws Exception {
//
//        customerDtoConverter = Mockito.mock(CustomerDtoConverter.class);
//        customerRepository = Mockito.mock(CustomerRepository.class);
//
//        customerService = new CustomerService(customerRepository ,customerDtoConverter);
//    }
//
//    @Test
//    public void whenCreateCustomerCalledWithValidRequest_itShouldValidReturnCustomerDto(){
//
//
//        CreateCustomerRequest  createCustomerRequest = new CreateCustomerRequest();
//        createCustomerRequest.setId("51772693358");
//        createCustomerRequest.setName("Ahmet Fatih");
//        createCustomerRequest.setSurName("Güzeller");
//        createCustomerRequest.setDateOfBirth("1998");
//        createCustomerRequest.setCity(CityDto.İstanbul);
//        createCustomerRequest.setAddress("Ev Adresi");
//
//        Customer customer = new Customer();
//        customer.setId(createCustomerRequest.getId());
//        customer.setName(createCustomerRequest.getName());
//        customer.setSurName(createCustomerRequest.getSurName());
//        customer.setDateOfBirth(createCustomerRequest.getDateOfBirth());
//        customer.setCity(City.valueOf(createCustomerRequest.getCity().name()));
//        customer.setAddress(createCustomerRequest.getAddress());
//
//
//        CustomerDto customerDto = new CustomerDto();
//        customerDto.setId("51772693358");
//        customerDto.setName("Ahmet Fatih");
//        customerDto.setSurName("Güzeller");
//        customerDto.setDateOfBirth("1998");
//        customerDto.setCity(CityDto.İstanbul);
//        customerDto.setAddress("Ev Adresi");
//
//
//     Mockito.when(customerService.createCustomer(createCustomerRequest)).thenReturn(customerDto);
//     Mockito.when(customerRepository.save(customer)).thenReturn(customer);
//     Mockito.when(customerDtoConverter.convert(customer)).thenReturn(customerDto);
//
//
//     CustomerDto result = customerService.createCustomer(createCustomerRequest);
//
//        Assert.assertEquals(result , customerDto);
//
//    }
//
//
//    @Test
//    public void whenCalledWithId_itShouldReturnCustomer(){
//
//        Customer customer = new Customer();
//
//        customer.setId("51772693358");
//        customer.setName("Ahmet Fatih");
//        customer.setSurName("Güzeller");
//        customer.setDateOfBirth("1999");
//        customer.setCity(City.Ankara);
//        customer.setAddress("Ev Adresi");
//
//
//        Mockito.when(customerRepository.findById("51772693358")).thenReturn(Optional.of(customer));
//
//
//        Customer result = customerService.getCustomerById("51772693358");
//
//        assertEquals(result , customer);
//
//        Mockito.verify(customerRepository).findById("51772693358");
//
//    }
//
//    @Test
//    public void whenCalledWithOutId_itShouldReturnEmptyCustomer(){
//
//        Customer customer = new Customer();
//
//        customer.setId("51772693358");
//        customer.setName("Ahmet Fatih");
//        customer.setSurName("Güzeller");
//        customer.setDateOfBirth("1999");
//        customer.setCity(City.Ankara);
//        customer.setAddress("Ev Adresi");
//
//        Mockito.when(customerRepository.findById("51772693358")).thenReturn(Optional.ofNullable(Customer.builder().build()));
//
//        Customer result = customerService.getCustomerById("51772693358");
//        Customer expectedCustomer= Customer.builder().build();
//
//        Assert.assertEquals(result  , expectedCustomer);
//
//   //     Mockito.verify(customerRepository.findById("51772693358"));
//    }
//
//    @Test
//    public void whenCalledWithIdAndValidRequest_itShouldReturnValidCustomerDto(){
//
//
//        UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest();
//        updateCustomerRequest.setName("Ahmet Fatih");
//        updateCustomerRequest.setSurName("Güzeller");
//        updateCustomerRequest.setCity(CityDto.İstanbul);
//        updateCustomerRequest.setAddress("Ev Adresi");
//
//        Customer customer = new Customer();
//        customer.setName(updateCustomerRequest.getName());
//        customer.setSurName(updateCustomerRequest.getSurName());
//        customer.setCity(City.valueOf(updateCustomerRequest.getCity().name()));
//        customer.setAddress(updateCustomerRequest.getAddress());
//
//
//        CustomerDto customerDto = new CustomerDto();
//        customerDto.setId("51772693358");
//        customerDto.setName("Ahmet Fatih");
//        customerDto.setSurName("Güzeller");
//        customerDto.setDateOfBirth("1998");
//        customerDto.setCity(CityDto.İstanbul);
//        customerDto.setAddress("Ev Adresi");
//
//
//        Mockito.when(customerRepository.findById("51772693358")).thenReturn(Optional.of(customer));
//        //ifPresent !!!!!!!
//        Mockito.when(customerDtoConverter.convert(customer)).thenReturn(customerDto);
//
//        CustomerDto result = customerService.updateCustomer("51772693358",updateCustomerRequest);
//
//        Assert.assertEquals(result , customerDto);
//
//        //Mockito.verify(customerService).updateCustomer("51772693358",updateCustomerRequest);
//        Mockito.verify(customerRepository).findById("51772693358");
//        Mockito.verify(customerRepository).save(customer);
//        Mockito.verify(customerDtoConverter).convert(customer);
//
//
//    }
//
//  @Test
//  public void whenCalledWithIdAndValidRequest_itShouldReturnEmptyCustomerDto(){
//      UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest();
//      updateCustomerRequest.setName("Ahmet Fatih");
//      updateCustomerRequest.setSurName("Güzeller");
//      updateCustomerRequest.setCity(CityDto.İstanbul);
//      updateCustomerRequest.setAddress("Ev Adresi");
//
//      Customer customer = new Customer();
//      customer.setName(updateCustomerRequest.getName());
//      customer.setSurName(updateCustomerRequest.getSurName());
//      customer.setCity(City.valueOf(updateCustomerRequest.getCity().name()));
//      customer.setAddress(updateCustomerRequest.getAddress());
//
//
//      CustomerDto customerDto = new CustomerDto();
//      customerDto.setId("51772693358");
//      customerDto.setName("Ahmet Fatih");
//      customerDto.setSurName("Güzeller");
//      customerDto.setDateOfBirth("1998");
//      customerDto.setCity(CityDto.İstanbul);
//      customerDto.setAddress("Ev Adresi");
//
//
//       Mockito.when(customerRepository.findById("51772693358")).thenReturn(Optional.of(Customer.builder().build()));
//
//       CustomerDto result = customerService.updateCustomer("51772693358" , updateCustomerRequest);
//       CustomerDto expectedCustomerDto = CustomerDto.builder().build();
//
//       Assert.assertEquals(result , expectedCustomerDto);
//
//    }
//
//     @Test
//     public void whenCalledWithIdCustomerDto_itShouldReturnCustomerDto(){
//
//         CustomerDto customerDto = new CustomerDto();
//         customerDto.setId("51772693358");
//         customerDto.setName("Ahmet Fatih");
//         customerDto.setSurName("Güzeller");
//         customerDto.setDateOfBirth("1998");
//         customerDto.setCity(CityDto.İstanbul);
//         customerDto.setAddress("Ev Adresi");
//
//         Customer customer = new Customer();
//         customer.setId(customerDto.getId());
//         customer.setName(customerDto.getName());
//         customer.setSurName(customerDto.getSurName());
//         customer.setDateOfBirth(customerDto.getDateOfBirth());
//         customer.setCity(City.valueOf(customerDto.getCity().name()));
//         customer.setAddress(customerDto.getAddress());
//
//         Mockito.when(customerRepository.findById("51772693358")).thenReturn(Optional.of(customer));
//         Mockito.when(customerDtoConverter.convert(customer)).thenReturn(customerDto);
//
//         CustomerDto result = customerService.customerGetById("51772693358");
//
//         Assert.assertEquals(result , customerDto);
//
//         Mockito.verify(customerRepository);
//
//     }
//    @Test
//    public void whenCalledWithIdCustomerDto_itShouldReturnEmptyCustomerDto(){
//
//        CustomerDto customerDto = new CustomerDto();
//        customerDto.setId("51772693358");
//        customerDto.setName("Ahmet Fatih");
//        customerDto.setSurName("Güzeller");
//        customerDto.setDateOfBirth("1998");
//        customerDto.setCity(CityDto.İstanbul);
//        customerDto.setAddress("Ev Adresi");
//
//        Customer customer = new Customer();
//        customer.setId(customerDto.getId());
//        customer.setName(customerDto.getName());
//        customer.setSurName(customerDto.getSurName());
//        customer.setDateOfBirth(customerDto.getDateOfBirth());
//        customer.setCity(City.valueOf(customerDto.getCity().name()));
//        customer.setAddress(customerDto.getAddress());
//
//
//        Mockito.when(customerRepository.findById("51772693358")).thenReturn(Optional.ofNullable(Customer.builder().build()));
//
//        CustomerDto result = customerService.customerGetById("51772693358");
//        CustomerDto expectedCustomerDto = CustomerDto.builder().build();
//        Assert.assertEquals(result , expectedCustomerDto);
//
//      //  Mockito.verify(customerService).customerGetById("51772693358");
//        Mockito.verify(customerRepository).findById("51772693358");
//
//        //devam edilecek !!!!!!!!!!!
//
//
   }
//
//
//
//
//
//}