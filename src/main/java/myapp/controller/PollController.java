package myapp.controller;

import myapp.security.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/polls")
public class PollController {
    @Autowired
    private PollService pollService;

    @GetMapping("")
    public String listPolls() {
        return "polls";
    }



}
