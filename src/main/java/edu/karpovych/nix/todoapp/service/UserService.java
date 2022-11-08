package edu.karpovych.nix.todoapp.service;

import edu.karpovych.nix.todoapp.dto.UserDto;
import edu.karpovych.nix.todoapp.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
