package dev.backend.pattimpulu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHECKLIST_ITEM")
public class ChecklistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, message = "Title must be at least 3 characters long")
    private String title;

    private String description;
    private boolean completed;

    @Version
    private Integer version;
}
