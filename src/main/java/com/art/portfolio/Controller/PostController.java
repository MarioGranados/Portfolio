package com.art.portfolio.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.art.portfolio.Model.Post;
import com.art.portfolio.Model.User;
import com.art.portfolio.Repository.PostRepo;
import com.art.portfolio.Repository.UserRepo;

@Controller
public class PostController {

    @Value("${file-upload-path}")
    private String uploadPath;

    private final UserRepo userRepo;
    private final PostRepo postRepo;

    public PostController(UserRepo userRepo, PostRepo postRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute Post post, @RequestParam(name = "file") MultipartFile uploadedFile, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        uploadPath += user.getId().toString();


        String filename = uploadedFile.getOriginalFilename();
       // String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(uploadPath, filename);

        File directory = new File(uploadPath);
        if (! directory.exists()){
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        try {
            uploadedFile.transferTo(destinationFile);
            System.out.println("suc");
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("not");
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        post.setUser(user);
        post.setImageUrl(uploadPath);
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
        user.setPassword("somepassword");
        List<Post> posts = postRepo.findByUserId(user.getId());
        model.addAttribute("posts", posts);
        return "gallery";
    }
}
