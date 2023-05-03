package com.art.portfolio.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        Pattern pattern = Pattern.compile(("[^a-zA-Z]"));
        Matcher matcher = pattern.matcher(user.getPassword());
        if(matcher.find() && user.getPassword().length() > 8) {
            System.out.println("user was created");
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            userRepo.save(user);
        } else {
            System.out.println("password not strong enough");
        }
        return "redirect:/login";
    }
    
    @GetMapping("/profile")
    public String getProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "profile";
    }

    @GetMapping("/profile/{username}")
    public String searchByProfileName(@PathVariable String username, Model model) {
        User user = userRepo.findByUsername(username);
        //lets not have any access to passwords through here
        user.setPassword(passwordEncoder.encode("You thought it would be that easy?"));
        model.addAttribute("user", user);
        return "user";
    }
}
