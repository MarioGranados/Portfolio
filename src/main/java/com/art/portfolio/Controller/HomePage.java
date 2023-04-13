package com.art.portfolio.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.art.portfolio.Repository.PostRepo;
import com.art.portfolio.Repository.UserRepo;


@Controller
public class HomePage {
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public HomePage(PostRepo postRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }
    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("post", postRepo.findAll());
        model.addAttribute("user", userRepo.findAll());
        return "index";
    }
}
