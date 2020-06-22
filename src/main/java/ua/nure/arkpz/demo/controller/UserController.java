package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.nure.arkpz.demo.dto.UserDto;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.role.Role;
import ua.nure.arkpz.demo.service.AuthenticationService;
import ua.nure.arkpz.demo.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
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
                                                  @RequestBody UserDto userDto,
                                                  @AuthenticationPrincipal User authenticatedUser) {
        User existingUser = userService.findByUserId(userId);
        if (!existingUser.equals(authenticatedUser)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        existingUser = userService.updateUser(existingUser, userDto);
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<User> updateUserPassword(@PathVariable Long userId,
                                                  @RequestBody UserDto userDto,
                                                  @AuthenticationPrincipal User currentUser) {
        User existingUser = userService.findByUserId(userId);
        if (!existingUser.equals(currentUser)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        existingUser = authenticationService.updatePassword(existingUser, userDto.getPassword());
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

    @GetMapping("api/users/all")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam Long userId,
                                                  @AuthenticationPrincipal User user) {
        User existingUser = userService.findByUserId(userId);
        if (!existingUser.equals(user) || !existingUser.getRoles().contains(Role.SUPERADMIN)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else return ResponseEntity.ok(userService.findAll());
    }

}
