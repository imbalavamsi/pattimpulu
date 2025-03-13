package dev.backend.pattimpulu.repository;

import dev.backend.pattimpulu.model.ChecklistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long> {
    List<ChecklistItem> findByTitleContainingIgnoreCase(String title);
}
