package com.art.portfolio.Controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.art.portfolio.Model.Post;
import com.art.portfolio.Model.User;
import com.art.portfolio.Repository.PostRepo;
import com.art.portfolio.Repository.UserRepo;

@Controller
public class PostController {

    private final UserRepo userRepo;
    private final PostRepo postRepo;

    public PostController(UserRepo userRepo, PostRepo postRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postRepo.save(post);
        return "post";
    }
    @GetMapping("/post/create")
    public String getCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post";
    }


    /* --------------------------------- gallery -------------------------------- */
    @GetMapping("/gallery/{username}")
    public String showGallery(Model model, @PathVariable String username) {
        User user = userRepo.findByUsername(username);
        List<Post> posts = postRepo.findByUserId(user.getId());
        model.addAttribute("posts", posts);
        return "gallery";
    }
}
