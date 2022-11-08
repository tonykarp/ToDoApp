package edu.karpovych.nix.todoapp.repository;

import edu.karpovych.nix.todoapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
