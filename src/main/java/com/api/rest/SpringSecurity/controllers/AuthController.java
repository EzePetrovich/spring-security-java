package com.api.rest.SpringSecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

    @GetMapping("/helloAuth")
    public String helloAuth() {
        return "Hello auth!";
    }

    @GetMapping("/helloAdmin")
    public String helloAdmin() {
        return "Hello admin!";
    }

}
