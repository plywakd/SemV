package com.pai.controller;

import com.pai.models.Account;
import com.pai.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testInfo(){
        return "WORKING";
    }

    @PostMapping("/user/registration")
    public Account registerUser(@RequestParam("name") String name,
                                @RequestParam("lastName") String lastname,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password) {
        return accountService.insertUser(new Account(name, lastname, email, password, LocalDateTime.now(), "", false));
    }

}
