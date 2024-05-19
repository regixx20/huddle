package myapp.controller;

import myapp.model.*;
import myapp.service.ParticipantService;
import myapp.service.UserService;

import java.util.*;

import org.springframework.ui.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
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
    private ParticipantService participantService;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("")
    public String listPolls() {
        return "dashboard";
    }

    @ModelAttribute("polls")
    Collection<Poll> polls(Principal principal) {
        if(principal == null){
            return pollService.findAllPolls();
        }
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

    @ModelAttribute
    public Slot slotForVote(
            @RequestParam(value = "idSlot", required = false) Long id) {
        if (id != null) {
            return slotService.findSlotById(id);
        }

        return new Slot();
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

        if (slotsJson == null || slotsJson.isEmpty()) {
            bindingResult.rejectValue("slots", "error.slots", "Veuillez sélectionner au moins un créneau horaire.");
            return "newPoll";
        }

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


    @PostMapping("/participate/{id}/vote")
    public String vote(@PathVariable("id") String id, @RequestParam Map<String, String> allParams, @RequestParam("participant") String participant) {
        Poll poll = pollService.findPollById(id);




        //supprimer les deux derniers éléments de allParams
        allParams.remove("participant");
        allParams.remove("_csrf");

        Iterator<Slot> slotIterator = poll.getSlots().iterator();
        for (String key : allParams.keySet()) {
            if (slotIterator.hasNext()) {
                Slot slot = slotIterator.next();
                slot.setParticipantVote(allParams.get(key));
            }
        }
        logger.info("allParams : " + allParams);


        for (Participant p : poll.getParticipants()) {

            System.out.println(p.getEmail());
            if (p.getEmail().equals(participant)) {

                logger.info("Vous avez déjà voté");
                return "voted";
            }
        }

        Participant p = new Participant();
        p.setEmail(participant);
        participantService.saveParticipant(p);
        poll.getParticipants().add(p);
        poll.setNumberOfParticipants(poll.getNumberOfParticipants() + 1);

        pollService.savePoll(poll);
        logger.info("le nombre de participants est : " + poll.getNumberOfParticipants());

        logger.info("liste de slots : " + poll.getSlots());
        //pollService.savePoll(poll);



        return "redirect:/";

    }



    @RequestMapping("/delete")
    public String deletePoll(@RequestParam(value = "id") String id) {
        Poll p = pollService.findPollById(id);
        pollService.deletePoll(p);
        return "redirect:/meeting";

    }


}
