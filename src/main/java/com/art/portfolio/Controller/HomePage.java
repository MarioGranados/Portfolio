package com.art.portfolio.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.art.portfolio.Model.Category;
import com.art.portfolio.Repository.CategoryRepo;
import com.art.portfolio.Repository.PostRepo;
import com.art.portfolio.Repository.UserRepo;


@Controller
public class HomePage {
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;

    public HomePage(PostRepo postRepo, 
    UserRepo userRepo, 
    CategoryRepo categoryRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }
    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("post", postRepo.findAll());
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "About";
    } 
}
