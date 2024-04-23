package myapp.controller;

import myapp.model.Poll;
import myapp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/polls")
public class PollController {
    @Autowired
    private PollService pollService;

    @GetMapping("")
    public String listPolls() {
        return "dashboard";
    }

    @ModelAttribute("polls")
    Collection<Poll> polls() {
        return pollService.findAllPolls();
    }

    @ModelAttribute
    public Poll newPoll(
             @RequestParam(value = "id", required = false) Long id){
        if (id != null) {
            return pollService.findPollById(id);
        }
        Poll poll = new Poll();
        poll.setTitle("");
        poll.setDescription("");
        poll.setLocation("");
        return poll;


    }

    @GetMapping("/edit")
    public String editPoll(@ModelAttribute Poll p) {
        return "newPoll";
    }

    @PostMapping("/edit")
    public String savePoll(@ModelAttribute Poll p, BindingResult result) {
        if(result.hasErrors()) {
            return "newPoll";
        }
        pollService.savePoll(p);
        return "redirect:/polls";
    }

    @RequestMapping("/delete")
    public String deletePoll(@RequestParam(value = "id") Long id) {
        Poll p = pollService.findPollById(id);
        pollService.deletePoll(p);
        return "redirect:/polls";


    }


}
