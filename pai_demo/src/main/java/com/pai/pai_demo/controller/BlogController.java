package com.pai.pai_demo.controller;

import com.pai.pai_demo.dtos.PostDto;
import com.pai.pai_demo.model.Account;
import com.pai.pai_demo.model.Post;
import com.pai.pai_demo.service.AccountService;
import com.pai.pai_demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BlogController {
    AccountService accountService;
    PostService postService;

    @Autowired
    public BlogController(AccountService accountService, PostService postService) {
        this.accountService = accountService;
        this.postService = postService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hello world!";
    }

    @GetMapping("/user={userName}&number={number}")
    public String helloMe(
            @PathVariable("userName") String userName,
            @PathVariable("number") Integer number
    ) {
        return String.format("name = %s number = %d", userName, number);
    }

    @PostMapping("/users/registration")
    public Account registerAccount(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        return accountService.insertAccount(new Account(name, lastName, email, password, LocalDateTime.now(), "", false));
    }

    @GetMapping("/users")
    public List<Account> getAllAccounts() {
        return accountService.selectAccounts();
    }

    @PutMapping("/users/activate/id={userId}")
    public boolean activateAccount(
            @PathVariable("accountId") int accountId
    ) {
        return accountService.activateAccount(accountId);
    }

    @DeleteMapping("/users/delete")
    public boolean deleteAccountById(
            @RequestParam("accountId") int accountId
    ) {
        return accountService.deleteAccountById(accountId);
    }

    @PostMapping("/posts/publication")
    public String addNewPost(@Valid @ModelAttribute("postDto") PostDto postDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "Validation errors! "+bindingResult.getFieldErrors().stream()
                    .map(err-> err.getField() + " : " + err.getDefaultMessage())
                    .collect(Collectors.joining("\n"));
        }
        if(postDto.getCategory() == null){
            return "Empty category";
        }
        if(!accountService.getAccountById(postDto.getAuthorId()).isPresent()){
            return "Invalid author id";
        }
        postService.addPost(postDto);
        return "OK";
    }
}

