package myapp;

import myapp.model.User;
import myapp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach()
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testfindAll() {
        User user = new User();
        userRepository.save(user);
        User user2 = new User();
        userRepository.save(user2);
        User user3 = new User();
        userRepository.save(user3);
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    void testfindById() {
        User user = new User();
        userRepository.save(user);
        assertEquals(user.getId(), userRepository.findById(user.getId()).get().getId());
    }

    @Test
    void testdeleteById() {
        User user = new User();
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    void testdeleteAll() {
        User user = new User();
        userRepository.save(user);
        userRepository.deleteAll();
        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    void testfindByEmail() {
        User user = new User();
        user.setEmail("setondji@gmail.com");
        userRepository.save(user);

        User foundUser = userRepository.findByEmail("setondji@gmail.com");

        assertEquals("setondji@gmail.com", foundUser.getEmail());
    }

    @Test
    void testfindByFirstName() {
        User user = new User();
        user.setFirstName("setondji");
        userRepository.save(user);

        User user2 = new User();
        user2.setFirstName("setondji");
        userRepository.save(user2);

        List<User> users = userRepository.findByFirstName("setondji");
        assertEquals(2, users.size());

    }

    @Test
    void testfindByLastName() {
        User user = new User();
        user.setLastName("setondji");
        userRepository.save(user);

        User user2 = new User();
        user2.setLastName("setondji");
        userRepository.save(user2);

        List<User> users = userRepository.findByLastName("setondji");
        assertEquals(2, users.size());

    }
}
