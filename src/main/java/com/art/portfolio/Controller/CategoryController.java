package com.art.portfolio.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.art.portfolio.Model.Post;
import com.art.portfolio.Repository.CategoryRepo;
import com.art.portfolio.Repository.PostRepo;

@Controller
public class CategoryController {
    private final CategoryRepo categoryRepo;
    private final PostRepo postRepo;

    public CategoryController(CategoryRepo categoryRepo, PostRepo postRepo) {
        this.categoryRepo = categoryRepo;
        this.postRepo = postRepo;
    }

    @GetMapping("/category/{category}") 
    public String getCategories(Model model, @PathVariable String category){
        model.addAttribute("post", postRepo.findAllPostsByCategories(category));
        return "gallery";  
    }
}
