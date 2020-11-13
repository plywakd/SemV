package com.pai.service;

import com.pai.models.Account;
import com.pai.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account insertUser(Account account){
        return accountRepository.save(account);
    }
}
