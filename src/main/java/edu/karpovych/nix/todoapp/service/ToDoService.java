package edu.karpovych.nix.todoapp.service;

import edu.karpovych.nix.todoapp.dto.ToDoDto;
import edu.karpovych.nix.todoapp.model.ToDo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ToDoService {

    List<ToDo> getToDosByUserEmail(String email);
    ToDo getToDoById(UUID id);
    void deleteToDo(UUID id);
    void saveToDo(ToDoDto toDoDto);
    void updateTodoStatus(UUID id, boolean status);
    ToDo findByDescription(String description);
}
