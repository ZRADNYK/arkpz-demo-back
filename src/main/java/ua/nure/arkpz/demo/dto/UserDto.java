package ua.nure.arkpz.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate birthDate;
    private String email;
}
