package myapp.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;
import myapp.model.Poll;
import myapp.model.Slot;
import myapp.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    protected final Log logger = LogFactory.getLog(getClass());

    @PostConstruct
    public void init() {
        User user0 = new User();
        user0.setEmail("yoann.augier@etu.univ-amu.fr");
        user0.setFirstName("Yoann");
        user0.setLastName("Augier");
        user0.setPassword("yoann");
        userService.saveUser(user0);

        User user1 = new User();
        user1.setEmail("setondji.mededji@etu.univ-amu.fr");
        user1.setFirstName("Setondji");
        user1.setLastName("Mededji");
        user1.setPassword("setondji");
        userService.saveUser(user1);

        User user2 = new User();
        user2.setEmail("vincent.nze@etu.univ-amu.fr");
        user2.setFirstName("Vincent");
        user2.setLastName("Nze");
        user2.setPassword("vincent");
        userService.saveUser(user2);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 6);
        Date limitDate = calendar.getTime();

        // Create a poll for user0 with limitDate set to 6 months from now
        Poll poll0 = new Poll();
        poll0.setTitle("Sondage pour Yoann");
        poll0.setDescription("Ceci est un sondage pour Yoann");
        poll0.setLocation("Marseille");
        poll0.setLimitDate(limitDate);

        poll0.setCreator(user0);
        pollService.savePoll(poll0);
        logger.info("id: " + poll0.getId());
        logger.info("creators: " + user0.getPolls());

        // Create a poll for user1 with limitDate set to 6 months from now
        Poll poll1 = new Poll();
        poll1.setTitle("Sondage pour Setondji");
        poll1.setDescription("Ceci est un sondage pour Setondji");
        poll1.setLocation("Paris");
        poll1.setLimitDate(limitDate);

        poll1.setCreator(user1);
        pollService.savePoll(poll1);
        logger.info("id: " + poll1.getId());

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



}
