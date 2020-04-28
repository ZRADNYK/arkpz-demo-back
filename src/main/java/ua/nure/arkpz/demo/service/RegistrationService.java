package ua.nure.arkpz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.UserDao;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.validator.OvalValidator;
import ua.nure.arkpz.demo.validator.OvalValidatorImpl;

@Service
public class RegistrationService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final OvalValidator validator;

    @Autowired
    public RegistrationService(UserDao userDao, PasswordEncoder passwordEncoder, OvalValidatorImpl validator) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }


    public User registerUser(User newUser) {
        User user = User.builder()
                .userId(null)
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .birthDate(newUser.getBirthDate())
                .email(newUser.getEmail())
                .token(null)
                .tokenExpirationDate(null)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
        validator.validate(user);
        userDao.save(user);
        return user;
    }

}
