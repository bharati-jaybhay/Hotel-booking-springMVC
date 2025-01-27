package com.sprk.sprk_hotels.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/listings/login")
    public String loginForm() {
        return "login";
    }
}
