package myapp.controller;

import myapp.model.Poll;
import myapp.model.User;
import myapp.service.PollService;
import myapp.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    protected final Log logger = LogFactory.getLog(getClass());


    @Autowired
    private PollService pollService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String dashboard(Model model, Principal principal){
        String email = principal.getName();
        Optional<User> optionalUser = userService.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
        }
        for (User user : userService.findAllUsers()) {
            logger.info("LES CREATEURS DE CHAQUE      USER  " + user.getPolls());
        }
        logger.info(userService.findAllUsers().toString());
        return "dashboard";
    }
    @ModelAttribute("polls")
    Collection<Poll> polls(Principal principal) {
        String email = principal.getName();
        Optional<User> optionalUser = userService.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getPolls();
        } else {
            return Collections.emptyList();
        }
    }





}
