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
    private MailService mailService;

    @GetMapping("/send-mail")
    public String sendMail(@RequestParam String senderEmail,
                           @RequestParam String recipientEmail,
                           @RequestParam String text) {
        User sender = new User();
        sender.setEmail(senderEmail);
        String subject = "Créneaux choisis ";
        // Split the recipient email addresses by comma and convert to a list
        List<String> recipients = Arrays.asList(recipientEmail.split(","));
        mailService.sendEmail(sender, recipients, subject, text);
        return "Email sent!";
    }
}
