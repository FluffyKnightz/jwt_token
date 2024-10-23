package com.test.jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

}
