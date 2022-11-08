package edu.karpovych.nix.todoapp.dto;

import edu.karpovych.nix.todoapp.model.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDto {
    @NotEmpty
    private String description;
    private boolean isDone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private User user;
}
