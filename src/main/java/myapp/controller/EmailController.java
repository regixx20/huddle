package myapp.controller;

import myapp.model.User;
import myapp.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;

@RestController
public class EmailController {


    @Autowired
    private MailService mailService;

    @GetMapping("/send-mail")
    public RedirectView sendMail(@RequestParam String senderEmail,
                                 @RequestParam String recipientEmail,
                                 @RequestParam String text,
                                 RedirectAttributes redirectAttributes) {
        User sender = new User();
        sender.setEmail(senderEmail);
        String subject = "Créneaux choisis ";

        List<String> recipients = Arrays.asList(recipientEmail.split(","));
        mailService.sendEmail(sender, recipients, subject, text);

        redirectAttributes.addFlashAttribute("message", "Email de confirmation envoyé");
        return new RedirectView("/dashboard");
    }
}
