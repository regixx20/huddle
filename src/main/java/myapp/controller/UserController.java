package myapp.controller;

import myapp.model.Participant;
import myapp.model.Poll;
import myapp.model.User;
import myapp.service.ParticipantService;
import myapp.service.RegisterValidator;
import myapp.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@Controller

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private AuthenticationProvider authenticationProvider;
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RegisterValidator registerValidator;





    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(User user, BindingResult bindingResult) {

        registerValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.saveUser(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        logger.info("users " + userService.findAllUsers());
        return "redirect:/";
    }




}
