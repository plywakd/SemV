package com.pai.pai_demo.service;

import com.pai.pai_demo.model.Account;
import com.pai.pai_demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;


    public Account insertAccount(Account account){
        return accountRepository.save(account);
    }

    public List<Account> selectAccounts(){
        return accountRepository.findAll(Sort.by(Sort.Direction.DESC, "registrationDateTime"));
    }

    public Optional<Account> getAccountById(int userId){
        return accountRepository.findById(userId);
    }

    public boolean activateAccount(int userId){
        boolean isActivated = false;
        Optional<Account> accountOptional = getAccountById(userId);
        if(accountOptional.isPresent()){
            Account accountToActivate = accountOptional.get();
            accountToActivate.setStatus(true);
            accountRepository.save(accountToActivate);
            isActivated = true;
        }
        return isActivated;
    }
    // DELETE FROM users WHERE user_id = ?;
    public boolean deleteAccountById(int userId){
        AtomicBoolean isDeleted = new AtomicBoolean(false);
        getAccountById(userId).ifPresent(user -> {
            accountRepository.deleteById(userId);
            isDeleted.set(true);
        });
        return isDeleted.get();
    }
}
