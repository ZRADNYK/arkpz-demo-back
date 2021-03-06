package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.role.Role;
import ua.nure.arkpz.demo.service.UserService;

@RestController
public class PermissionController {
    private final UserService userService;

    @Autowired
    public PermissionController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/permissions/{userId}/makeSuperadmin")
    public ResponseEntity<User> makeUserSuperadmin(@PathVariable Long userId,
                                                   @AuthenticationPrincipal User userDto) {
        User currentUser = userService.findByEmail(userDto.getEmail());
        if(!(currentUser.getAuthorities().contains(Role.ADMIN) || currentUser.getAuthorities().contains(Role.SUPERADMIN))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User existingUser = userService.findByUserId(userId);
        if(existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(existingUser.getRoles().contains(Role.ADMIN)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        existingUser = userService.makeUserSuperadmin(existingUser);
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

   // @Secured("ROLE_SUPERADMIN")
    @PatchMapping("/{userId}/makeAdmin")
    public ResponseEntity<User> makeUserAdmin(@PathVariable Long userId,
                                              @AuthenticationPrincipal User userDto) {
        User currentUser = userService.findByEmail(userDto.getEmail());
        if(!(currentUser.getAuthorities().contains(Role.ADMIN) || currentUser.getAuthorities().contains(Role.SUPERADMIN))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User existingUser = userService.findByUserId(userId);
        if(existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(existingUser.getRoles().contains(Role.ADMIN)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        existingUser = userService.makeUserAdmin(existingUser);
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

 //   @Secured("ROLE_SUPERADMIN")
    @PatchMapping("/{userId}/makeUser")
    public ResponseEntity<User> makeAdminUser(@PathVariable Long userId,
                                              @AuthenticationPrincipal User userDto) {
        User currentUser = userService.findByEmail(userDto.getEmail());
        if(!(currentUser.getAuthorities().contains(Role.ADMIN) || currentUser.getAuthorities().contains(Role.SUPERADMIN))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User existingUser = userService.findByUserId(userId);
        if(existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!existingUser.getRoles().contains(Role.ADMIN)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        existingUser = userService.makeAdminUser(existingUser);
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

}
