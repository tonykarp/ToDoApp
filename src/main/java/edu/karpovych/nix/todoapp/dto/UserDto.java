package edu.karpovych.nix.todoapp.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
