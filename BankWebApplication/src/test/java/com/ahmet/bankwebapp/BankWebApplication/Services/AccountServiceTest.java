package com.ahmet.bankwebapp.BankWebApplication.Services;

import com.ahmet.bankwebapp.BankWebApplication.Dto.AccountDto;
import com.ahmet.bankwebapp.BankWebApplication.Dto.AccountDtoConverter;
import com.ahmet.bankwebapp.BankWebApplication.Dto.CreateAccountRequest;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Account;
import com.ahmet.bankwebapp.BankWebApplication.Entities.City;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Currency;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Customer;
import com.ahmet.bankwebapp.BankWebApplication.Repositories.AccountRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AccountServiceTest {

    private AccountService accountService;

    private AccountRepository accountRepository;
    private CustomerService customerService;
    private AccountDtoConverter accountDtoConverter;



    //Test Kısmı refactor edilecek !

    @org.junit.Before
    public void setUp() throws Exception {

        accountRepository = Mockito.mock(AccountRepository.class);
        customerService = Mockito.mock(CustomerService.class);
        accountDtoConverter = Mockito.mock(AccountDtoConverter.class);

      //  accountService= new AccountService(accountRepository ,customerService ,accountDtoConverter, rabbitTemplate);

    }


     @Test
     public void whencreateAccountCalledWithValidRequest_itShouldValidReturnAccountDto(){


         CreateAccountRequest createAccountRequest = new CreateAccountRequest();
         createAccountRequest.setId("1");
        // createAccountRequest.setCustomerId("51772693358");
         createAccountRequest.setCity(City.Ankara);
         createAccountRequest.setBalance(100.0);
         createAccountRequest.setCurrency(Currency.TL);

         Customer customer = new Customer();
     //    customer.setId("51772693358");
         customer.setName("Ahmet Fatih");
         customer.setSurName("Güzeller");
         customer.setDateOfBirth("1999");
         customer.setCity(City.Ankara);
      //   customer.setAddress("Ev Adresi");

         Account account = new Account();

         account.setId(createAccountRequest.getId());
         account.setCustomerId(createAccountRequest.getCustomerId());
         account.setBalance(createAccountRequest.getBalance());
         account.setCity(City.valueOf(createAccountRequest.getCity().name()));
         account.setCurrency(createAccountRequest.getCurrency());

         AccountDto accountDto = new AccountDto();

         accountDto.setId("1");
      //   accountDto.setCustomerId("51772693358");
         accountDto.setBalance(100.0);
         accountDto.setCurrency(Currency.TL);
         accountDto.setCity(City.Ankara);

       //  Mockito.when(customerService.getCustomerById("51772693358")).thenReturn(customer);
         Mockito.when(accountRepository.save(account)).thenReturn(account);
         Mockito.when(accountDtoConverter.convert(account)).thenReturn(accountDto);



         AccountDto result = accountService.createAccount(createAccountRequest);
         Assert.assertEquals(result ,accountDto);

       //  Mockito.verify(customerService).getCustomerById("51772693358");
         Mockito.verify(accountRepository).save(account);
         Mockito.verify(accountDtoConverter).convert(account);

     }

    @Test
    public void whenCreateAccountCalledWithNonExistCustomer_itShouldReturnEmptyAccountDto(){


        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setId("1");
        //createAccountRequest.setCustomerId("51772693358");
        createAccountRequest.setCity(City.Ankara);
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCurrency(Currency.TL);

        Customer customer = new Customer();
        //customer.setId("51772693358");
        customer.setName("Ahmet Fatih");
        customer.setSurName("Güzeller");
        customer.setDateOfBirth("1999");
        customer.setCity(City.Ankara);
      //  customer.setAddress("Ev Adresi");

        Account account = new Account();

        account.setId(createAccountRequest.getId());
        account.setCustomerId(createAccountRequest.getCustomerId());
        account.setBalance(createAccountRequest.getBalance());
        account.setCity(City.valueOf(createAccountRequest.getCity().name()));
        account.setCurrency(createAccountRequest.getCurrency());


     //   Mockito.when(customerService.getCustomerById("51772693358")).thenReturn(Customer.builder().build());

        AccountDto result = accountService.createAccount(createAccountRequest);
        AccountDto expectedAccountDto = AccountDto.builder().build();

        Assert.assertEquals(result , expectedAccountDto);

   //     Mockito.verify(customerService).getCustomerById("51772693358");

        Mockito.verifyNoInteractions(accountRepository);
        Mockito.verifyNoInteractions(accountDtoConverter);
    }

    @Test
    public void whenCreateAccountCalledWithCustomerWithOutId_itShouldReturnEmptyAccountDto(){

        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setId("1");
       // createAccountRequest.setCustomerId("51772693358");
        createAccountRequest.setCity(City.Ankara);
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCurrency(Currency.TL);

        Customer customer = new Customer();
     //   customer.setId("51772693358");
        customer.setName("Ahmet Fatih");
        customer.setSurName("Güzeller");
        customer.setDateOfBirth("1999");
        customer.setCity(City.Ankara);
      //  customer.setAddress("Ev Adresi");

        Account account = new Account();

        account.setId(createAccountRequest.getId());
        account.setCustomerId(createAccountRequest.getCustomerId());
        account.setBalance(createAccountRequest.getBalance());
        account.setCity(City.valueOf(createAccountRequest.getCity().name()));
        account.setCurrency(createAccountRequest.getCurrency());


       // Mockito.when(customerService.getCustomerById("51772693358")).thenReturn(Customer.builder()
       //                 .id(" ")
        //                .build());

        AccountDto result = accountService.createAccount(createAccountRequest);
        AccountDto expectedAccountDto = AccountDto.builder().build();

        Assert.assertEquals(result , expectedAccountDto);

     //   Mockito.verify(customerService).getCustomerById("51772693358");

        Mockito.verifyNoInteractions(accountRepository);
        Mockito.verifyNoInteractions(accountDtoConverter);
    }


    //Create







    }


