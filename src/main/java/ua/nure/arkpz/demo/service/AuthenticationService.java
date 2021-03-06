package ua.nure.arkpz.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.UserDao;
import ua.nure.arkpz.demo.model.User;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;

    @Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                                 UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
    }

    public boolean authenticate(User user, User userFromDb) {
        if (passwordEncoder.matches(user.getPassword(), userFromDb.getPassword())) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            return true;
        }
        return false;
    }

    public User updatePassword(User existingUser, String password) {
        existingUser.setPassword(passwordEncoder.encode(password));
        userDao.save(existingUser);
        return existingUser;
    }
}
