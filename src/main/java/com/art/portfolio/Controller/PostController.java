package com.art.portfolio.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping("/gallery/{id}")
    public String getPostId() {
        return "post";
    }

}
