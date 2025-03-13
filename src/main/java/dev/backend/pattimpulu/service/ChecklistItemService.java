package dev.backend.pattimpulu.service;

import dev.backend.pattimpulu.exception.ResourceNotFoundException;
import dev.backend.pattimpulu.model.ChecklistItem;
import dev.backend.pattimpulu.repository.ChecklistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChecklistItemService {
    @Autowired
    private ChecklistItemRepository repository;

    public List<ChecklistItem> getAllItems() {
        return repository.findAll();
    }

    public ChecklistItem getItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Checklist item not found with ID: " + id));
    }

    public ChecklistItem createItem(ChecklistItem item) {
        item.setId(null); // Ensure it's a new record
        return repository.save(item);
    }

    public List<ChecklistItem> createItems(List<ChecklistItem> items) {
        items.forEach(item -> item.setId(null)); // Ensure new records are created
        return repository.saveAll(items);
    }


    public ChecklistItem updateItem(Long id, ChecklistItem itemDetails) {
        ChecklistItem existingItem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Checklist item not found with ID: " + id));

        // Update title only if provided and not blank
        if (itemDetails.getTitle() != null && !itemDetails.getTitle().isBlank()) {
            existingItem.setTitle(itemDetails.getTitle());
        }

        // Update description only if provided and not blank
        if (itemDetails.getDescription() != null && !itemDetails.getDescription().isBlank()) {
            existingItem.setDescription(itemDetails.getDescription());
        }

        // Always update completion status & increment version
        existingItem.setCompleted(itemDetails.isCompleted());
        existingItem.setVersion(existingItem.getVersion() + 1);

        return repository.save(existingItem);
    }




    public void deleteItemById(Long id) {
        ChecklistItem item = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Checklist item not found with ID: " + id));

        repository.delete(item);
    }

    public List<ChecklistItem> searchItems(String keyword) {
        return repository.findByTitleContainingIgnoreCase(keyword);
    }
}
