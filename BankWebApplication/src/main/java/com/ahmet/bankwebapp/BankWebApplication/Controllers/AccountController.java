package com.ahmet.bankwebapp.BankWebApplication.Controllers;

import com.ahmet.bankwebapp.BankWebApplication.Dto.AccountDto;
import com.ahmet.bankwebapp.BankWebApplication.Dto.CreateAccountRequest;
import com.ahmet.bankwebapp.BankWebApplication.Dto.MoneyTransferRequest;
import com.ahmet.bankwebapp.BankWebApplication.Dto.UpdateAccountRequest;
import com.ahmet.bankwebapp.BankWebApplication.Services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest){

        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> accountGetById(@PathVariable String id){

        return ResponseEntity.ok(accountService.accountGetById(id));

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDto>> accountGetAll(){

        return ResponseEntity.ok(accountService.accountGetAll());
    }
    @PutMapping("update/{updateAccountRequest}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable  String id , @RequestBody UpdateAccountRequest updateAccountRequest){

     return ResponseEntity.ok(accountService.updateAccount(id ,updateAccountRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id){

        accountService.deleteAccount(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/addMoney/{id}/{amount}")
    public ResponseEntity<AccountDto> addMoney(@PathVariable String id , @PathVariable Double amount){
        return ResponseEntity.ok(accountService.addMoney(id , amount));
    }
    @PutMapping("/withDrawMoney/{id}/{amount}")
    public ResponseEntity<AccountDto> widthDrawMoney(@PathVariable String id , @PathVariable Double amount){
        return ResponseEntity.ok(accountService.withDrawMoney(id,amount));
    }

///*{moneyTransferRequest}
    @PutMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody MoneyTransferRequest moneyTransferRequest){

       accountService.transferMoneyMessage(moneyTransferRequest);

       return ResponseEntity.ok("Processed !");

    }





}
