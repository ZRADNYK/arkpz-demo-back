package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.service.AuthenticationService;
import ua.nure.arkpz.demo.service.UserService;
import ua.nure.arkpz.demo.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class AuthenticationController {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtTokenUtil jwtTokenUtil, UserService userService,
                                    AuthenticationService authenticationService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) {
        User userFromDb = userService.loadUserByUsername(user.getEmail());

        if (authenticationService.authenticate(user, userFromDb)) {
            final String token = jwtTokenUtil.generateToken(user);
            userFromDb.setToken(token);
            userFromDb.setTokenExpirationDate(jwtTokenUtil.getExpirationDateFromToken(token));
            return ResponseEntity.ok(userFromDb);
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
