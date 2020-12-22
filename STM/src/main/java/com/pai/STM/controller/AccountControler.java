package com.pai.STM.controller;

import com.pai.STM.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountControler {

    AccountService accountService;

    @Autowired
    public AccountControler(AccountService accountService){
        this.accountService = accountService;
    }
}
