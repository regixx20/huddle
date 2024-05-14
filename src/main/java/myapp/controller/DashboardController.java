package myapp.controller;

import myapp.model.Poll;
import myapp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private PollService pollService;
    @RequestMapping("")
    public String dashboard(){
        return "dashboard";
    }
    @ModelAttribute("polls")
    Collection<Poll> polls() {
        return pollService.findAllPolls();
    }

}
