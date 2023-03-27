package com.art.portfolio.Controller;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.art.portfolio.Model.User;
import com.art.portfolio.Repository.UserRepo;

@Controller
public class UserController {

    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;

    public UserController(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userRepo.save(user);
        return "redirect:/login";
    }
    
}
