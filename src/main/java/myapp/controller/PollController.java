package myapp.controller;

import ch.qos.logback.core.model.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import myapp.model.Poll;
import myapp.model.PollValidator;
import myapp.model.Slot;
import myapp.service.PollService;
import myapp.service.SlotService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/polls")
public class PollController {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PollService pollService;

    @Autowired
    private PollValidator pollValidator;

    @Autowired
    private SlotService slotService;
    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

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
       // poll.setSlots(new ArrayList<>());
        return poll;


    }

    @GetMapping("/edit")
    public String editPoll(@ModelAttribute Poll p, Model model) {
        return "newPoll";
    }

    @PostMapping("/edit")
    public String savePoll(@RequestParam("slots") String slotsJson, @ModelAttribute Poll p, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        pollValidator.validate(p, bindingResult);
        if (bindingResult.hasFieldErrors("title")) {
            return "newPoll";
        }
      /*  if (slotsJson == null || slotsJson.isEmpty()) {
            // JSON vide ou nul
            // Gérer la situation de manière appropriée, par exemple en renvoyant une erreur
            return "redirect:/polls";

        }*/

        try {
           List<Slot> slots = objectMapper.readValue(slotsJson, new TypeReference<List<Slot>>() {});
            logger.info(p.getTitle());
            logger.info(p.getTitle());
            logger.info(p.getLocation());

            p.setSlots(slots);
            logger.info(p.getSlots());
            logger.info(slotService.findAllSlots());
            pollService.savePoll(p);

            return "redirect:/polls"; // Redirection en cas de succès
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/"; // Redirection en cas d'erreur
        }
    }





    @RequestMapping("/delete")
    public String deletePoll(@RequestParam(value = "id") Long id) {
        Poll p = pollService.findPollById(id);
        pollService.deletePoll(p);
        return "redirect:/polls";

    }
    @GetMapping("/details")
    public  String showDetails(@RequestParam(value = "id") Long id) {
    return "pollDetails";
    }
}
