package com.pai.pai_demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {
    @GetMapping("/xd")
    public String home(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        System.out.println("credentials: " + userDetails);
        return "home";
    }

    @GetMapping("login")
    public String login() {
        return "loginPage";
    }
}

