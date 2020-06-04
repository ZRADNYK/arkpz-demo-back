package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.demo.dto.UserDto;
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
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        final UserDetails userDetails = userService.loadUserByUsername(userDto.getEmail());

        if (userDetails.getUsername().equals(userDto.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User registeredUser = registrationService.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }
}
