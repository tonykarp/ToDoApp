package edu.karpovych.nix.todoapp.service.impl;

import edu.karpovych.nix.todoapp.dto.ToDoDto;
import edu.karpovych.nix.todoapp.model.ToDo;
import edu.karpovych.nix.todoapp.model.User;
import edu.karpovych.nix.todoapp.repository.ToDoRepository;
import edu.karpovych.nix.todoapp.repository.UserRepository;
import edu.karpovych.nix.todoapp.service.ToDoService;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;
@Service
public class ToDoServiceImpl implements ToDoService {
    ToDoRepository toDoRepository;
    UserRepository userRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository,UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ToDo> getToDosByUserEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalStateException("User id doesn't exist");
        }
        return toDoRepository.findAllByUserOrderByTargetDate(user);


    }

    @Override
    public ToDo getToDoById(UUID id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDo id doesn't exist"));
    }

    @Override
    public void deleteToDo(UUID id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDo id doesn't exist"));
        toDoRepository.delete(toDo);
    }

    @Override
    public void saveToDo(ToDoDto toDoDto) {
        ToDo todo = new ToDo();
        todo.setDescription(toDoDto.getDescription());
        todo.setDone(false);
        todo.setTargetDate(toDoDto.getTargetDate());
        todo.setUser(toDoDto.getUser());
        toDoRepository.save(todo);
    }
    @Override
    public void updateTodoStatus(UUID id, boolean status) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDo id doesn't exist"));
        toDo.setDone(status);
        toDoRepository.save(toDo);
    }
    @Override
    public ToDo findByDescription(String description) {
        return toDoRepository.findByDescription(description);
    }

}

