package dev.backend.pattimpulu.controller;

import dev.backend.pattimpulu.model.ChecklistItem;
import dev.backend.pattimpulu.service.ChecklistItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checklist")
@Tag(name = "Checklist Controller", description = "CRUD operations for checklist items")
public class ChecklistItemController {

    @Autowired
    private ChecklistItemService service;

    @Operation(summary = "Get all checklist items")
    @GetMapping
    public ResponseEntity<List<ChecklistItem>> getAllItems() {
        List<ChecklistItem> items = service.getAllItems();
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Operation(summary = "Get a checklist item by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ChecklistItem> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getItemById(id));
    }

    @Operation(summary = "Create a new checklist item")
    @PostMapping
    public ResponseEntity<ChecklistItem> createItem(@Valid @RequestBody ChecklistItem item) {
        ChecklistItem savedItem = service.createItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a checklist item")
    @PutMapping("/{id}")
    public ResponseEntity<ChecklistItem> updateItem(@PathVariable Long id, @Valid @RequestBody ChecklistItem item) {
        ChecklistItem updatedItem = service.updateItem(id, item);
        return ResponseEntity.ok(updatedItem);
    }

    @Operation(summary = "Delete a checklist item")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable Long id) {
        service.deleteItemById(id);
        return ResponseEntity.ok("Item deleted successfully!");
    }

    @Operation(summary = "Search checklist items by title")
    @GetMapping("/search")
    public ResponseEntity<List<ChecklistItem>> searchItems(@RequestParam String keyword) {
        List<ChecklistItem> items = service.searchItems(keyword);
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Create multiple checklist items")
    @PostMapping("/bulk")
    public ResponseEntity<List<ChecklistItem>> createItems(@RequestBody List<ChecklistItem> items) {
        List<ChecklistItem> savedItems = service.createItems(items);
        return new ResponseEntity<>(savedItems, HttpStatus.CREATED);
    }

}
