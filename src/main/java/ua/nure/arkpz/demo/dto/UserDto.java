package ua.nure.arkpz.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// TODO
@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate birthDate;
    private String email;
}
