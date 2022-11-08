package edu.karpovych.nix.todoapp.repository;

import edu.karpovych.nix.todoapp.model.ToDo;
import edu.karpovych.nix.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, UUID> {
    ToDo findByDescription(String description);
    List<ToDo> findAllByUserOrderByTargetDate(User user);




}
