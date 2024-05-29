package myapp;

import myapp.model.User;
import myapp.repository.UserRepository;
import myapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllUsers() {
        User user1 = new User();
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setEmail("user2@example.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.findAllUsers();
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserById(1L);
        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindUserByEmail() {
        User user = new User();
        user.setEmail("user@example.com");
        when(userRepository.findByEmail("user@example.com")).thenReturn(user);

        User foundUser = userService.findUserByEmail("user@example.com");
        assertNotNull(foundUser);
        assertEquals("user@example.com", foundUser.getEmail());
        verify(userRepository, times(1)).findByEmail("user@example.com");
    }

    @Test
    public void testFindUserByFirstName() {
        User user = new User();
        user.setFirstName("John");
        when(userRepository.findByFirstName("John")).thenReturn(Arrays.asList(user));

        List<User> users = userService.findUserByfirstName("John");
        assertEquals(1, users.size());
        assertEquals("John", users.get(0).getFirstName());
        verify(userRepository, times(1)).findByFirstName("John");
    }

    @Test
    public void testFindUserByLastName() {
        User user = new User();
        user.setLastName("Doe");
        when(userRepository.findByLastName("Doe")).thenReturn(Arrays.asList(user));

        List<User> users = userService.findUserBylastName("Doe");
        assertEquals(1, users.size());
        assertEquals("Doe", users.get(0).getLastName());
        verify(userRepository, times(1)).findByLastName("Doe");
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        userService.saveUser(user);

        assertNotNull(user.getPassword());
        assertNotEquals("password", user.getPassword()); // Check that password is encoded
        verify(userRepository, times(1)).save(user);
    }
}
