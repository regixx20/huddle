package myapp.controller;

import myapp.model.User;
import myapp.service.UserService;

import java.util.*;

import org.springframework.ui.Model;
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
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/meeting")
public class PollController {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PollService pollService;

    @Autowired
    private PollValidator pollValidator;

    @Autowired
    private SlotService slotService;

    @Autowired
    private UserService userService;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("")
    public String listPolls() {
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
    @ModelAttribute
    public Poll newPoll(
             @RequestParam(value = "id", required = false) String id){
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
    public String savePoll(Model model, @RequestParam("slots") String slotsJson, @RequestParam("creator") String creator, @ModelAttribute Poll p, BindingResult bindingResult, Principal principal) {



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

            if (principal != null) {
                model.addAttribute("email", principal.getName());
                String email = principal.getName(); // Récupère le nom d'utilisateur du Principal
                //User creatorConnected = userService.findUserByEmail(email);
                //creatorConnected.setEmail(email);
                userService.findUserByEmail(email).ifPresent(p::setCreator);

                //userService.saveUser(p.getCreator());

                p.getCreator().getPolls().add(p);





            }
            else {
                User creatorNotConnected = new User();
                creatorNotConnected.setEmail(creator);
                userService.saveUser(creatorNotConnected);
                p.setCreator(creatorNotConnected);

            }

            pollService.savePoll(p);


            logger.info("createur : " + p.getCreator().getEmail());

           for (Slot slot : slots) {
               slot.setPoll(p);
               slotService.saveSlot(slot);
           }


            logger.info(p.getSlots());
            logger.info(slotService.findAllSlots());
            logger.info("les slots sont : " + slotService.findAllSlots());

            for (Poll s : pollService.findPollByTitle("Sondage pour Setondji")){
                logger.info("le createur de setondji est : " + s.getCreator().getEmail());

            }
            logger.info("le createur de setondji est : " + pollService.findPollByTitle("Sondage pour Setondji"));
            return "redirect:/meeting"; // Redirection en cas de succès
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/"; // Redirection en cas d'erreur
        }
    }

    @GetMapping("/organize/{id}")
    public String showDetails(@PathVariable("id") String id, Model model) {
        Poll poll = pollService.findPollById(id);
        if (poll != null) {
            model.addAttribute("poll", poll);

            return "pollDetails";
        } else {
            return "redirect:/meeting"; // Redirection si le poll n'est pas trouvé
        }
    }

    @GetMapping("/participate/{id}")
    public String participate() {
        return "redirect:/meeting/participate/{id}/vote";
    }




    @GetMapping("/participate/{id}/vote")
    public String vote(@PathVariable("id") String id, Model model, Principal principal) {
        Poll poll = pollService.findPollById(id);
        if (poll == null) {
            return "redirect:/dashboard"; // Redirection si le poll n'est pas trouvé
        }
        model.addAttribute("poll", poll);

        if (principal == null) {
            logger.info("slots je");
            return "vote";
        }
        else if (principal.getName().equals(pollService.findPollById(id).getCreator().getEmail())) {
            return "redirect:/meeting/organize/{id}";
        }
        else {
            logger.info("slots je"+ poll.getSlots());
            return "vote";
        }

    }

    /*@GetMapping("/participate/{id}/vote")
    public String vote(@PathVariable("id") String id, Model model, Principal principal) {
        Poll poll = pollService.findPollById(id);
        if (poll == null) {
            return "redirect:/meeting"; // Redirection si le poll n'est pas trouvé
        }

        // À ce stade, `poll` est non null
        model.addAttribute("poll", poll);

        if (principal == null) {
            return "vote"; // Afficher la page de vote si aucun utilisateur n'est connecté
        }

        // Vérifier si l'utilisateur connecté est le créateur du poll
        if (principal.getName().equals(poll.getCreator().getEmail())) {
            return "redirect:/meeting/organize/" + id; // Redirection spécifique pour le créateur
        }

        return "redirect:/dashboard"; // Redirection par défaut pour les autres utilisateurs connectés
    }*/





    @RequestMapping("/delete")
    public String deletePoll(@RequestParam(value = "id") String id) {
        Poll p = pollService.findPollById(id);
        pollService.deletePoll(p);
        return "redirect:/meeting";

    }


}
