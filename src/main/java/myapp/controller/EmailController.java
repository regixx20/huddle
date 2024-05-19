package myapp.controller;

import myapp.model.User;
import myapp.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class EmailController {

    @Autowired
    private MailService emailService;

    @Autowired
    private MailService mailService;

    @GetMapping("/send-mail")
    public String sendMail(@RequestParam String senderEmail,
                           @RequestParam String recipientEmail,
                           @RequestParam String subject,
                           @RequestParam String text) {
        User sender = new User();
        sender.setEmail(senderEmail);
        mailService.sendEmail(sender, Arrays.asList(recipientEmail), subject, text);
        return "Email sent!";
    }
}
