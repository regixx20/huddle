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

import java.util.ArrayList;

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
        user2.setFirstName("Vicent");
        user2.setLastName("Nze");
        user2.setPassword("vincent");
        userService.saveUser(user2);

        //Create a poll for user0 and user1 and user2
        Poll poll = new Poll();
        poll.setTitle("Sondage pour Yoann");
        poll.setDescription("Ceci est un sondage pour Yoann");
        poll.setLocation("Marseille");
        poll.setCreator(user0);
        pollService.savePoll(poll);
        logger.info("id: " + poll.getId());
        logger.info("creators: " + user0.getPolls());

        poll = new Poll();
        poll.setTitle("Sondage pour Setondji");
        poll.setDescription("Ceci est un sondage pour Setondji");
        poll.setLocation("Paris");
        poll.setCreator(user1);
        pollService.savePoll(poll);
        logger.info("id: " + poll.getId());

        poll = new Poll();
        poll.setTitle("Sondage pour Vincent");
        poll.setDescription("Ceci est un sondage pour Vincent");
        poll.setLocation("Bruxelles");
        poll.setCreator(user2);
        pollService.savePoll(poll);
        logger.info("id: " + poll.getId());



    }
}
