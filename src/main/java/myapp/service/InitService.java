package myapp.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;
import myapp.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class InitService {
    @Autowired
    private PollService pollService;

    @Autowired
    private SlotService slotService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private ParticipantService participantService;

    @PostConstruct
    public void init() {

        User user0 = getOrCreateUser(
                "yoann.augier@etu.univ-amu.fr",
                "Yoann",
                "Augier",
                "yoann");

        User user1 = getOrCreateUser(
                "setondji.mededji@etu.univ-amu.fr",
                "Setondji",
                "Mededji",
                "setondji");

        User user2 = getOrCreateUser(
                "vincent.nze@etu.univ-amu.fr",
                "Vincent",
                "Nze",
                "vincent");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        long millis = calendar.getTimeInMillis();
        // mettre la date limite à 6 mois
        Date limitDate = new Date(millis);

        List<Slot> slots = new ArrayList<>();
        Slot slot0 = new Slot();
        slot0.setStart(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
        // fin deux heures plus tard
        slot0.setEnd(slot0.getStart().plus(2, ChronoUnit.HOURS));


        Slot slot1 = new Slot();
        slot1.setStart(LocalDateTime.now().plus(2, ChronoUnit.DAYS));
        // fin deux heures plus tard
        slot1.setEnd(slot1.getStart().plus(2, ChronoUnit.HOURS));
        for (Slot slot : slots) {
            slotService.saveSlot(slot);
        }
        List<Vote> votes = new ArrayList<>();
        Vote vote0 = new Vote();
        vote0.setVote("yes");
        vote0.setSlot(slot0);
        votes.add(vote0);
        voteService.saveVote(vote0);


        // Create a poll for user0 with limitDate set to 6 months from now
        Poll poll0 = new Poll();
        poll0.setTitle("Sondage pour Yoann");
        poll0.setDescription("Ceci est un sondage pour Yoann");
        poll0.setLocation("Marseille");
        poll0.setLimitDate(limitDate);
        poll0.setSlots(slots);
        poll0.setNumberOfParticipants(3);



        poll0.setCreator(user0);
        pollService.savePoll(poll0);
        logger.info("id: " + poll0.getId());
        logger.info("creators: " + user0.getPolls());

        // Create a poll for user1 with limitDate set to 6 months from now
        Poll poll1 = new Poll();
        poll1.setTitle("Election de délégué de classe");
        poll1.setDescription("Le but de ce sondage est d'élire le délégué de classe pour l'année scolaire 2024-2025");
        poll1.setLocation("Fac de sciences de Luminy");
        poll1.setLimitDate(limitDate);
        poll1.setSlots(slots);
        slot0.setPoll(poll1);
        slot1.setPoll(poll1);

        poll1.setCreator(user1);

        pollService.savePoll(poll1);

        Poll poll3 = new Poll();
        poll3.setTitle("Choix du projet de fin d'année");
        poll3.setDescription("Ce sondage a pour but de choisir le projet de fin d'année pour le cours de développement logiciel.");
        poll3.setLocation("Institut Supérieur d'Informatique");
        poll3.setLimitDate(limitDate);
        poll3.setSlots(slots);
        
        slot0.setPoll(poll3);
        slot1.setPoll(poll3);

        poll3.setCreator(user1);

        pollService.savePoll(poll3);
        logger.info("id: " + poll1.getId());

        Poll poll4 = new Poll();
        poll4.setTitle("Rendez-vous au parc");
        poll4.setDescription("Je voudrais organiser un rendez-vous au parc pour passer un bon moment avec vous tous ensemble.");
        poll4.setLocation("Parc Borély");
        poll4.setLimitDate(limitDate);
        poll4.setSlots(slots);
        poll4.setCreator(user1);
        pollService.savePoll(poll4);

        // Create a poll for user2 with limitDate set to 6 months from now
        Poll poll2 = new Poll();
        poll2.setTitle("Sondage pour Vincent");
        poll2.setDescription("Ceci est un sondage pour Vincent");
        poll2.setLocation("Bruxelles");
        poll2.setLimitDate(limitDate);

        poll2.setCreator(user2);
        pollService.savePoll(poll2);
        logger.info("id: " + poll2.getId());


    }

    private User getOrCreateUser(String email, String firstName, String lastName, String password) {
        User existingUser = userService.findUserByEmail(email);
        if (existingUser != null) {
            return existingUser;
        }


        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        userService.saveUser(user);
        return user;
    }


}
