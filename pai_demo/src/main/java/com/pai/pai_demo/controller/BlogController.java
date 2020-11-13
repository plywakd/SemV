package com.pai.pai_demo.controller;

import com.pai.pai_demo.model.Account;
import com.pai.pai_demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BlogController {
    AccountService accountService;
    @Autowired
    public BlogController(AccountService accountService) {
        this.accountService = accountService;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "Hello world!";
    }
    @GetMapping("/user={userName}&number={number}")
    public String helloMe(
            @PathVariable("userName") String userName,
            @PathVariable("number") Integer number
    ){
        return String.format("name = %s number = %d", userName, number);
    }
    @PostMapping("/users/registration")
    public Account registerAccount(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ){
        return accountService.insertAccount(new Account(name,lastName,email,password, LocalDateTime.now(), "",false));
    }
    @GetMapping("/users")
    public List<Account> getAllAccounts(){
        return accountService.selectAccounts();
    }
    @PutMapping("/users/activate/id={userId}")
    public boolean activateAccount(
            @PathVariable("accountId") int accountId
    ){
        return accountService.activateAccount(accountId);
    }
    @DeleteMapping("/users/delete")
    public boolean deleteAccountById(
            @RequestParam("accountId") int accountId
    ){
        return accountService.deleteAccountById(accountId);
    }
}

