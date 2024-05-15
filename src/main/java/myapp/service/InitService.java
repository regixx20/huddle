package myapp.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;
import myapp.model.Poll;
import myapp.model.Slot;
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

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;


    @PostConstruct
    public void init() {
        Poll p = new Poll();
        p.setTitle("First Poll");
        p.setDescription("This is the first poll");
        p.setLocation("Marseille");

        /*//attribuer des exemples de slots
        ArrayList<Slot> slots = new ArrayList<>();
        slots.add(new Slot("2021-12-01T08:00:00", "2021-12-01T09:00:00"));
        p.setSlots(slots);
        for (Slot s : slots) {
            s.setPoll(p);

        }*/

        pollService.savePoll(p);
        logger.info("slots: " + slotService.findAllSlots());
        logger.info("id: " + p.getId());
        /*Slot s = new Slot("2021-12-01T10:00:00", "2021-12-01T11:00:00");
        s.setPoll(p);
        slotService.saveSlot(s);
        logger.info("slots: " + slotService.findAllSlots());*/


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
