package myapp;

import myapp.model.Poll;
import myapp.repository.PollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PollRepositoryTest {

    @Autowired
    private PollRepository pollRepository;

    @BeforeEach
    public void setUp() {
        pollRepository.deleteAll();
        Poll poll1 = new Poll();
        poll1.setTitle("Title1");
        poll1.setDescription("Description1");
        poll1.setLocation("Location1");
        poll1.setLimitDate(new Date());
        pollRepository.save(poll1);

        Poll poll2 = new Poll();
        poll2.setTitle("Title2");
        poll2.setDescription("Description2");
        poll2.setLocation("Location2");
        poll2.setLimitDate(new Date());
        pollRepository.save(poll2);
        Poll poll3 = new Poll();
        poll3.setTitle("Title3");
        poll3.setDescription("Description3");
        poll3.setLocation("Location3");
        poll3.setLimitDate(new Date());
        pollRepository.save(poll3);

    }

    @Test
    public void testDeleteAll() {
        pollRepository.deleteAll();
        List<Poll> polls = pollRepository.findAll();
        assertEquals(3, polls.size());
    }


    @Test
    public void testFindAll() {
        List<Poll> polls = pollRepository.findAll();
        assertEquals(6, polls.size());
    }


}
