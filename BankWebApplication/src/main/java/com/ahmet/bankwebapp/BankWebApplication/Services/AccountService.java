package com.ahmet.bankwebapp.BankWebApplication.Services;


import com.ahmet.bankwebapp.BankWebApplication.Dto.*;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Account;
import com.ahmet.bankwebapp.BankWebApplication.Entities.City;
import com.ahmet.bankwebapp.BankWebApplication.Entities.Customer;
import com.ahmet.bankwebapp.BankWebApplication.Exceptions.AccountNotFoundException;
import com.ahmet.bankwebapp.BankWebApplication.Exceptions.CustomerNotFoundException;
import com.ahmet.bankwebapp.BankWebApplication.Repositories.AccountRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;

    private final DirectExchange exchange;
    private final AmqpTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.routingKey}")
    String routingKey;

   @Value("${spring.rabbitmq.queue}")
   String firstStepQueue;

   @Value("${spring.rabbitmq.queue2}")
   String secondStepQueue;

    @Value("${spring.rabbitmq.queue3}")
    String thirdStepQueue;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter accountDtoConverter, DirectExchange exchange, AmqpTemplate amqpTemplate) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConverter = accountDtoConverter;

        this.exchange = exchange;
        this.rabbitTemplate = amqpTemplate;
    }


    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {

        Customer customer = customerService.getCustomerById(createAccountRequest.getCustomerId());

        if (customer.getId() == null || customer.getId().equals("")) {

            throw new CustomerNotFoundException("Customer Not Found !");

        } else {

            Account account = new Account();

            account.setId(createAccountRequest.getId());
            account.setCustomerId(createAccountRequest.getCustomerId());
            account.setBalance(createAccountRequest.getBalance());
            account.setCity(City.valueOf(createAccountRequest.getCity().name()));
            account.setCurrency(createAccountRequest.getCurrency());

            return accountDtoConverter.convert(accountRepository.save(account));

        }
    }

    public AccountDto accountGetById(String id) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        return optionalAccount.map(accountDtoConverter::convert)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found "));
    }

    public List<AccountDto> accountGetAll() {

        List<Account> accountList = accountRepository.findAll();

        return accountList.stream()
                .map(accountDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public AccountDto updateAccount(String id, UpdateAccountRequest updateAccountRequest) {

        Optional<Account> optionalAccount = accountRepository.findById(id);
        optionalAccount.ifPresent(account -> {

            account.setBalance(updateAccountRequest.getBalance());
            account.setCity(City.valueOf(updateAccountRequest.getCity().name()));
            account.setCurrency(updateAccountRequest.getCurrency());

            accountRepository.save(account);
        });
        //exception fırlatılacak !
        return optionalAccount.map(accountDtoConverter::convert)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found !.."));
    }


    public void deleteAccount(String id) {
        accountRepository.deleteById(id);

    }

    public AccountDto addMoney(String id, Double amount) {

        Optional<Account> optionalAccount = accountRepository.findById(id);
        optionalAccount.ifPresent(account ->
        {
            account.setBalance(account.getBalance() + amount
            );

            accountRepository.save(account);
        });

        return optionalAccount.map(accountDtoConverter::convert)
                .orElseThrow(() -> new AccountNotFoundException("Customer Not Found !"));
    }

    public AccountDto withDrawMoney(String id, Double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        optionalAccount.ifPresent(account -> {
            if (account.getBalance() > amount) {

                account.setBalance(account.getBalance() - amount);

                accountRepository.save(account);
            } else {

                //exception fırlatılacak !
                System.out.println("Insufficient Balance");
            }

        });

        return optionalAccount.map(accountDtoConverter::convert)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found !!!!!"));
    }

    public void transferMoney(MoneyTransferRequest moneyTransferRequest) {

        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, moneyTransferRequest);
    }


    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void transferMoneyMessage(MoneyTransferRequest transferRequest) {

        Optional<Account> optionalAccount = accountRepository.findById(transferRequest.getSenderToId());

        optionalAccount.ifPresentOrElse(account -> {
                    if (account.getBalance() > transferRequest.getAmount()) {

                        account.setBalance(account.getBalance() - transferRequest.getAmount());

                        accountRepository.save(account);

                        rabbitTemplate.convertAndSend(exchange.getName(), "secondRoutingKey", transferRequest);
                    } else {
                        System.out.println("Insufficient Balance !");
                    }
                },
                () -> System.out.println(" Account Not Found  !")
        );

    }


  @RabbitListener(queues = "${spring.rabbitmq.queue2}" )
  public void updateReceiverAccount(MoneyTransferRequest moneyTransferRequest) {

      Optional<Account> optionalAccount = accountRepository.findById(moneyTransferRequest.getFromToId());

      optionalAccount.ifPresentOrElse(from -> {


                  from.setBalance(from.getBalance() + moneyTransferRequest.getAmount());

                  accountRepository.save(from);

                  rabbitTemplate.convertAndSend(exchange.getName(),secondStepQueue , moneyTransferRequest);


              },
              () -> {

                      System.out.println("Account Not Found !");

               }
                       );

   }

    @RabbitListener(queues ="${spring.rabbitmq.queue3}")
    public void finalizeTransfer(MoneyTransferRequest moneyTransferRequest) {

        Optional<Account> optionalAccount = accountRepository.findById(moneyTransferRequest.getFromToId());

        optionalAccount.ifPresent(from ->{

            System.out.println("New Balance : " + from.getBalance());

        } );


     Optional<Account> accountOptional = accountRepository.findById(moneyTransferRequest.getSenderToId());

     accountOptional.ifPresent(sender -> {

         System.out.println("New Account :  " + sender.getBalance());
     });

          }

    }



