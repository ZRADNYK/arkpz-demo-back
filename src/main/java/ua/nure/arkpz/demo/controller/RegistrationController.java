package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.service.RegistrationService;
import ua.nure.arkpz.demo.service.UserService;


@RestController
@CrossOrigin
public class RegistrationController {
    private final UserService userService;
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userDTO) {
        final UserDetails userDetails = userService.loadUserByUsername(userDTO.getEmail());

        if (userDetails.getUsername().equals(userDTO.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User registeredUser = registrationService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }
}
