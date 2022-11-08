package edu.karpovych.nix.todoapp.repository;

import edu.karpovych.nix.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    Optional<User> findById(UUID id);
}
