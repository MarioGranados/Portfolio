package com.art.portfolio.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.art.portfolio.Model.Post;
import com.art.portfolio.Model.User;

@Service("mailService")
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Value("${spring-mail-from}")
    private String from;

    private final String websiteName = "myArtStuff-do-not-reply";

    public String generateRandomCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public void verificationEmail(User user, String verificationCode) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(user.getEmail());
        msg.setSubject(websiteName + " Verification Code");
        msg.setText(user.getFirstName() + ", Here is your verification code: " + verificationCode +
        " Please do not share this code with anyone. This code is valid for 24 hours."
        );

        try {
            this.emailSender.send(msg);
        } catch ( MailException e) {
            System.err.println(e.getMessage());
        }
    }

    public void prepareAndSend(Post post, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(post.getUser().getEmail());
        msg.setSubject(subject);
        msg.setText(body);

        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}
