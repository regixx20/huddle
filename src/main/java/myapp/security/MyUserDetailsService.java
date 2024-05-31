package myapp.security;

import myapp.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import myapp.model.User;
import myapp.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //logger.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + userService.findUserByEmail(email).getEmail());
        User user = userService.findUserByEmail(email);
                //.orElseThrow(() -> new UsernameNotFoundException(email));
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .disabled(false)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();
    }
}
