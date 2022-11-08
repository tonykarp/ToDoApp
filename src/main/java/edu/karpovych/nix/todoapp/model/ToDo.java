package edu.karpovych.nix.todoapp.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean isDone;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

}
