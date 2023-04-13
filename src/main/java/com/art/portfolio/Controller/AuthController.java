package com.art.portfolio.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}

