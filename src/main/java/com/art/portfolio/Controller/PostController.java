package com.art.portfolio.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.art.portfolio.Model.Category;
import com.art.portfolio.Model.Post;
import com.art.portfolio.Model.Tag;
import com.art.portfolio.Model.User;
import com.art.portfolio.Repository.CategoryRepo;
import com.art.portfolio.Repository.PostRepo;
import com.art.portfolio.Repository.TagRepo;
import com.art.portfolio.Repository.UserRepo;

@Controller
public class PostController {

    @Value("${file-upload-path}")
    private String uploadPath;

    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;
    private final PasswordEncoder passwordEncoder;
    private final TagRepo tagRepo;

    public PostController(UserRepo userRepo,
            PostRepo postRepo,
            PasswordEncoder passwordEncoder,
            CategoryRepo categoryRepo,
            TagRepo tagRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepo = categoryRepo;
        this.tagRepo = tagRepo;

    }

    @PostMapping("/upload")
    public String createPost(
        @RequestParam(name = "file") MultipartFile uploadedFile,
        @RequestParam(name = "tag") String tag,
        @RequestParam(name = "category") String category,
        @ModelAttribute Post post) {

            System.out.println("category" + category);
        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String filename = uploadedFile.getOriginalFilename();
        // String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(uploadPath, filename);

        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        try {
            uploadedFile.transferTo(destinationFile);
            System.out.println("sucess " + destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }

        if(!tag.isEmpty()) {
            List<String> separatedByCommas = Arrays.asList(tag.split(", "));
            List<Tag> tags = new ArrayList<>();
            for(int i = 0; i < separatedByCommas.size(); i++) {
                tags.add(new Tag(separatedByCommas.get(i)));
                tagRepo.save(tags.get(i));
            }
            post.setTags(tags);
        }

        if(!category.isEmpty()) {
            List<String> separatedByCommas = Arrays.asList(category.split(", "));
            List<Category> categories = new ArrayList<>();
            for(int i = 0; i < separatedByCommas.size(); i++) {
                categories.add(new Category(separatedByCommas.get(i)));
                categoryRepo.save(categories.get(i));
            }
            post.setCategories(categories);
            
        }

        post.setUser(user);
        post.setPostUrl("images/" + filename);
        postRepo.save(post);
        return "upload";
    }

    @GetMapping("/upload")
    public String getCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "upload";
    }

    /* --------------------------------- gallery -------------------------------- */

    @GetMapping("/gallery/{username}")
    public String showGallery(Model model, @PathVariable String username) {
        User user = userRepo.findByUsername(username);
        user.setPassword("");
        List<Post> posts = postRepo.findByUserId(user.getId());
        model.addAttribute("posts", posts);
        return "gallery";
    }

    @GetMapping("/gallery")
    public String allGallery(Model model) {
        model.addAttribute("post", postRepo.findAll());
        return "gallery";
    }

    @GetMapping("/gallery/{searchQuery}")
    public String showResults(Model model, @PathVariable String searchQuery) {
        model.addAttribute("posts", postRepo.findAllByResults(searchQuery));
        return "gallery";
    }

    @GetMapping("/post/{postId}")
    public String showPost(Model model, @PathVariable Long postId) {
        Post post = postRepo.findPostById(postId);
        model.addAttribute("post", post);
        return "post";
    }

}
