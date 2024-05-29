package myapp.controller;

import myapp.model.*;
import myapp.service.*;

import java.util.*;

import org.springframework.ui.Model;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.logging.Logger;

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
    private VoteService voteService;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("")
    public String listPolls() {
        return "dashboard";
    }

    @ModelAttribute("polls")
    Collection<Poll> polls(Principal principal) {
        String email = principal.getName();
        User user = userService.findUserByEmail(email);

        return user.getPolls();
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
    public String editPoll(@ModelAttribute Poll p, Model model, Principal principal) {
        model.addAttribute("email", principal.getName());
        return "newPoll";
    }

    @PostMapping("/edit")
    public String savePoll(Model model, @RequestParam("slotsJson") String slotsJson, @RequestParam("creator") String creator, @ModelAttribute Poll p, BindingResult bindingResult, Principal principal) {


        logger.info("le createur est : " + creator);
        pollValidator.validate(p, bindingResult);
        if (bindingResult.hasFieldErrors("title")) {
            return "newPoll";
        }
        if (bindingResult.hasFieldErrors("description")) {
            return "newPoll";
        }
        if (bindingResult.hasFieldErrors("limitDate")) {
            return "newPoll";
        }


        if (slotsJson == null || slotsJson.isEmpty()) {
            bindingResult.rejectValue("slots", "error.slots", "Veuillez sélectionner au moins un créneau horaire.");
            return "newPoll";
        }

        //Ajouter le créateur du sondage comme premier participant



        try {
           List<Slot> slots = objectMapper.readValue(slotsJson, new TypeReference<List<Slot>>() {});

           logger.info(p.getTitle());
           logger.info(p.getTitle());
           logger.info(p.getLocation());
            p.setSlots(slots);
            if (principal != null) {

                //logger.info("email : " + principal.getName());
                String email = principal.getName();

                User c = userService.findUserByEmail(email);
                if (creator != null) {
                    p.setCreator(c);
                    c.getPolls().add(p);
                }

            }
            else {
                User creatorNotConnected = new User();
                creatorNotConnected.setEmail(creator);
                userService.saveUser(creatorNotConnected);
                p.setCreator(creatorNotConnected);

            }

            pollService.savePoll(p);
            logger.info("createur : " + p.getCreator().getEmail());

            if(participantService.findParticipantByEmail(creator) == null) {
                Participant creatorParticipant = new Participant();
                creatorParticipant.setEmail(creator);
                creatorParticipant.setFirstName(userService.findUserByEmail(creator).getFirstName());
                creatorParticipant.setLastName(userService.findUserByEmail(creator).getLastName());
                p.getParticipants().add(creatorParticipant);
                participantService.saveParticipant(creatorParticipant);

                for (Slot slot : p.getSlots()) {
                    Vote vote = new Vote();
                    vote.setVote("yes");
                    vote.setParticipant(creatorParticipant);
                    vote.setSlot(slot);

                    slot.getVotes().add(vote);
                    creatorParticipant.getVotes().add(vote);


                    voteService.saveVote(vote);

                    slotService.saveSlot(slot);


                }
            }
            if(participantService.findParticipantByEmail(creator) != null) {
                Participant creatorParticipant = participantService.findParticipantByEmail(creator);

                for (Slot slot : p.getSlots()) {
                    Vote vote = new Vote();
                    vote.setVote("yes");
                    vote.setParticipant(creatorParticipant);
                    vote.setSlot(slot);

                    slot.getVotes().add(vote);
                    creatorParticipant.getVotes().add(vote);

                    voteService.saveVote(vote);
                    slotService.saveSlot(slot);
                }
            }

            logger.info(p.getSlots());
            logger.info(slotService.findAllSlots());
            logger.info("les slots sont : " + slotService.findAllSlots());
            logger.info("participants : " + voteService.findAllVotes());
            logger.info("slots");
            for (Slot s : slots) {
                logger.info("slot : " + s.getVotes());
            }
            logger.info("participantsssssss"+p.getParticipants());


            /*for (Poll s : pollService.findPollByTitle("Sondage pour Setondji")){
                logger.info("le createur de setondji est : " + s.getCreator().getEmail());

            }
            logger.info("le createur de setondji est : " + pollService.findPollByTitle("Sondage pour Setondji"));*/
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
    public String vote(@PathVariable("id") String id, @RequestParam Map<String, String> allParams, @RequestParam("participantEmail") String participantEmail,@RequestParam("participantlastName") String participantlastName, @RequestParam("participantfirstName") String participantfirstName){
        Poll poll = pollService.findPollById(id);

        //supprimer les deux derniers éléments de allParams
        allParams.remove("participant");
        allParams.remove("_csrf");
        Participant p = new Participant();
        p.setEmail(participantEmail);
        p.setFirstName(participantfirstName);
        p.setLastName(participantlastName);

        for (Participant par : poll.getParticipants()) {
            System.out.println(p.getEmail());
            if (par.getEmail().equals(p.getEmail())) {

                logger.info("Vous avez déjà voté");
                return "voted";
            }
        }


        participantService.saveParticipant(p);

        Iterator<Slot> slotIterator = poll.getSlots().iterator();
        for (String key : allParams.keySet()) {
            if (slotIterator.hasNext()) {
                Slot slot = slotIterator.next();
                Vote vote = new Vote();
                vote.setParticipant(p);
                vote.setSlot(slot);
                vote.setVote(allParams.get(key));
                slot.getVotes().add(vote);
                p.getVotes().add(vote);

                voteService.saveVote(vote);
            }
        }
        poll.getParticipants().add(p);
        poll.setNumberOfParticipants(poll.getNumberOfParticipants() + 1);

        pollService.savePoll(poll);
        logger.info("le nombre de participants est : " + poll.getNumberOfParticipants());
        logger.info("liste de slots : " + poll.getSlots());
        logger.info("liste de votes : " + voteService.findAllVotes());
        logger.info("liste de participants : " + participantService.findAllParticipants());
        logger.info("liste de participants : " + poll.getParticipants());

        for (Participant pt : poll.getParticipants()) {
            logger.info("listes des votes "+ pt.getVotes());
        }
        logger.info("All params : " + allParams);

        return "redirect:/";

    }

    @RequestMapping("/delete")
    public String deletePoll(@RequestParam(value = "id") String id) {
        Poll p = pollService.findPollById(id);
        pollService.deletePoll(p);
        return "redirect:/meeting";

    }


}
