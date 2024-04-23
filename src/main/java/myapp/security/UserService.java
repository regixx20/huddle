package myapp.security;


import myapp.model.User;
import myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User findUserByEmail(String emailAddress) {
        return userRepository.findByEmail(emailAddress);
    }

    public List<User> findUserByfirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public List<User> findUserBylastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

}
