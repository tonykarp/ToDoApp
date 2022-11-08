package edu.karpovych.nix.todoapp.controller;
import edu.karpovych.nix.todoapp.dto.ToDoDto;
import edu.karpovych.nix.todoapp.model.ToDo;
import edu.karpovych.nix.todoapp.model.User;
import edu.karpovych.nix.todoapp.repository.ToDoRepository;
import edu.karpovych.nix.todoapp.repository.UserRepository;
import edu.karpovych.nix.todoapp.service.ToDoService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class ToDoController {
    ToDoService toDoService;
    ToDoRepository toDoRepository;
    UserRepository userRepository;

    public ToDoController(ToDoService toDoService, ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoService = toDoService;
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/todos")
    public String listOfUsersToDO(Model model,
                                  @CurrentSecurityContext(expression="authentication?.name") String username){
        List<ToDo> todos = toDoService.getToDosByUserEmail(username);
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/todos/add")
    public String showAddingToDoForm(Model model){
        ToDoDto toDo = new ToDoDto();
        model.addAttribute("todo", toDo);
        return "addtodo";
    }
    @PostMapping("/todos/add/save")
    public String saveToDo(@Valid @ModelAttribute("todo") ToDoDto toDo,
                           BindingResult result,
                           @CurrentSecurityContext(expression="authentication?.name") String username,
                           Model model){
        ToDo existing = toDoService.findByDescription(toDo.getDescription());
        if (existing != null) {
            result.rejectValue("description", "2", "There is already an toDo with that description");
        }
        if (result.hasErrors()) {
            model.addAttribute("todo", toDo);
            return "addtodo";
        }
        User user = userRepository.findByEmail(username);
        toDo.setUser(user);
        toDoService.saveToDo(toDo);
        return "redirect:/todos";
    }
    @GetMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable(value = "id") UUID id) {
        toDoService.deleteToDo(id);
        return "redirect:/todos";
    }
    @PostMapping("/set-status/{id}/")
    public String setStatus(@PathVariable(value = "id")UUID id,@RequestParam boolean status){
        toDoService.updateTodoStatus(id, status);
        return "redirect:/todos";
    }

}
