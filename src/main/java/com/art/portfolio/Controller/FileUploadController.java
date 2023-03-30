package com.art.portfolio.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.art.portfolio.Model.User;

@Controller
public class FileUploadController {

    @Value("${file-upload-path}")
    private String uploadPath;

    @GetMapping("/fileupload")
    public String showUploadFileForm() {
        return "fileupload";
    }

    @PostMapping("/fileupload")
    public String saveFile(
        @RequestParam(name = "file") MultipartFile uploadedFile,
        Model model
    ) {
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
        return "fileupload";
    }
}
