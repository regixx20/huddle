package myapp.service;


import myapp.model.User;
import myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public void saveUser(User user) {
        var encoder = new BCryptPasswordEncoder();
        if (user.getPassword() != null ){
            user.setPassword(encoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }
}
