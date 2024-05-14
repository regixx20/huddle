package myapp;

import jakarta.transaction.Transactional;
import myapp.model.Poll;
import myapp.repository.PollRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PollRepositoryTest {

    @Autowired
    private PollRepository pollRepository;

    @BeforeEach
    void setUp() {
        pollRepository.deleteAll();
    }

    @AfterEach()
    void tearDown() {
        pollRepository.deleteAll();
    }

    @Test
    void testfindAll() {
        Poll poll = new Poll();
        pollRepository.save(poll);

        Poll poll2 = new Poll();
        pollRepository.save(poll2);
        assertEquals(2, pollRepository.findAll().size());
    }

    @Test
    void testfindById() {

        Poll poll = new Poll();
        poll.setTitle("title");
        System.out.println(poll.getId());
        pollRepository.save(poll);
    }

    @Transactional
    @Test
    void testdeleteById() {

        Poll poll = new Poll();
        pollRepository.save(poll);
        pollRepository.deleteById(poll.getId());
        assertEquals(0, pollRepository.findAll().size());
    }

    @Test
    void testdeleteAll() {

            Poll poll = new Poll();
            pollRepository.save(poll);
            Poll poll2 = new Poll();
            pollRepository.save(poll2);
            pollRepository.deleteAll();
            assertEquals(0, pollRepository.findAll().size());
    }

    @Test
    void findByTitle() {
        Poll poll = new Poll();
        poll.setTitle("title");
        pollRepository.save(poll);
        Poll poll2 = new Poll();
        poll2.setTitle("title");
        pollRepository.save(poll2);
        assertEquals(2, pollRepository.findByTitle("title").size());
    }

    @Test
    void findByTitleDescription() {
        Poll poll = new Poll();
        poll.setTitle("title");
        poll.setDescription("description");
        pollRepository.save(poll);
        Poll poll2 = new Poll();
        poll2.setTitle("title");
        poll2.setDescription("description");
        pollRepository.save(poll2);
        assertEquals(2, pollRepository.findByDescription("description").size());
    }


}
