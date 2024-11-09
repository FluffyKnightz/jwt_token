package com.test.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DemoController {

    @PostMapping("/demo")
    public ResponseEntity<String> hello(){
        return  ResponseEntity.ok("Hello World");
    }
}
