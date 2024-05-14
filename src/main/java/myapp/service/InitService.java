package myapp.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import myapp.model.Poll;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitService {
    @Autowired
    private PollService pollService;

    protected final Log logger = LogFactory.getLog(getClass());

    @PostConstruct
    public void init() {
        Poll p = new Poll();
        p.setTitle("First Poll");
        p.setDescription("This is the first poll");
        p.setLocation("Marseille");

        pollService.savePoll(p);
        logger.info("id: " + p.getId());


        p = new Poll();
        p.setTitle("Second Poll");
        p.setDescription("This is the second poll");
        p.setLocation("Paris");
        pollService.savePoll(p);
        logger.info("id: " + p.getId());


        p = new Poll();
        p.setTitle("Third Poll");
        p.setDescription("This is the third poll");
        p.setLocation("Bruxelles");
        pollService.savePoll(p);
    }
}
