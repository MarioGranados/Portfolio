package com.art.portfolio.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.art.portfolio.Repository.CategoryRepo;

@Controller
public class CategoryController {
    private final CategoryRepo categoryRepo;

    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/{category}") 
    public String getCategories(@PathVariable String category, Model model){
        model.addAttribute("categories", categoryRepo.findAll());
        return "gallery";  
    }
}
