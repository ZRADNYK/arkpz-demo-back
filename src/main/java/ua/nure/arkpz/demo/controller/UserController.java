package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.service.UserService;

@RestController("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId,
                                               @AuthenticationPrincipal User currentUser) {
        final User existingUser = userService.findByUserId(userId);
        if (!existingUser.equals(currentUser)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

    @PatchMapping("/{userId}/profile")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long userId,
                                                  @RequestBody User user,
                                                  @AuthenticationPrincipal User currentUser) {
        User existingUser = userService.findByUserId(userId);
        if (!existingUser.equals(currentUser)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        existingUser = userService.updateUser(existingUser, user);
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }



}
