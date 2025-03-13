package dev.backend.pattimpulu.service;

import dev.backend.pattimpulu.exception.ResourceNotFoundException;
import dev.backend.pattimpulu.model.ChecklistItem;
import dev.backend.pattimpulu.repository.ChecklistItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChecklistItemServiceTest {

    @Mock
    private ChecklistItemRepository repository;

    @InjectMocks
    private ChecklistItemService service;

    private ChecklistItem checklistItem;

    @BeforeEach
    void setUp() {
        checklistItem = new ChecklistItem(1L, "Test Task", "Description", false, 0);
    }

    @Test
    void testGetAllItems() {
        when(repository.findAll()).thenReturn(List.of(checklistItem));
        List<ChecklistItem> items = service.getAllItems();
        assertFalse(items.isEmpty());
        assertEquals(1, items.size());
    }

    @Test
    void testGetAllItems_EmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        List<ChecklistItem> items = service.getAllItems();
        assertTrue(items.isEmpty());
    }

    @Test
    void testGetItemById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(checklistItem));
        ChecklistItem item = service.getItemById(1L);
        assertNotNull(item);
        assertEquals("Test Task", item.getTitle());
    }

    @Test
    void testGetItemById_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getItemById(2L));
    }

    @Test
    void testCreateItem() {
        when(repository.save(any(ChecklistItem.class))).thenReturn(checklistItem);
        ChecklistItem savedItem = service.createItem(checklistItem);
        assertNotNull(savedItem);
        assertEquals("Test Task", savedItem.getTitle());
    }

    @Test
    void testCreateItem_WithNullValues() {
        ChecklistItem invalidItem = new ChecklistItem(null, "", "", false, 0);
        when(repository.save(any(ChecklistItem.class))).thenReturn(invalidItem);
        ChecklistItem savedItem = service.createItem(invalidItem);
        assertNotNull(savedItem);
    }

    @Test
    void testUpdateItem_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(checklistItem));
        when(repository.save(any(ChecklistItem.class))).thenReturn(checklistItem);

        ChecklistItem updatedItem = new ChecklistItem(1L, "Updated Task", "Updated Desc", true, 1);
        ChecklistItem result = service.updateItem(1L, updatedItem);

        assertEquals("Updated Task", result.getTitle());
        assertTrue(result.isCompleted());
    }

    @Test
    void testUpdateItem_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        ChecklistItem updatedItem = new ChecklistItem(2L, "Updated Task", "Updated Desc", true, 1);
        assertThrows(ResourceNotFoundException.class, () -> service.updateItem(2L, updatedItem));
    }

    @Test
    void testUpdateItem_OptimisticLockingFailure() {
        when(repository.findById(1L)).thenReturn(Optional.of(checklistItem));
        when(repository.save(any(ChecklistItem.class))).thenThrow(new ResourceNotFoundException("Row was updated or deleted"));

        ChecklistItem updatedItem = new ChecklistItem(1L, "Updated Task", "Updated Desc", true, 2);
        assertThrows(ResourceNotFoundException.class, () -> service.updateItem(1L, updatedItem));
    }

    @Test
    void testDeleteItemById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(checklistItem));
        doNothing().when(repository).delete(checklistItem);

        assertDoesNotThrow(() -> service.deleteItemById(1L));
    }

    @Test
    void testDeleteItemById_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.deleteItemById(2L));
    }

    @Test
    void testSearchItems_Found() {
        when(repository.findByTitleContainingIgnoreCase("Task")).thenReturn(List.of(checklistItem));
        List<ChecklistItem> items = service.searchItems("Task");
        assertFalse(items.isEmpty());
        assertEquals(1, items.size());
    }

    @Test
    void testSearchItems_NotFound() {
        when(repository.findByTitleContainingIgnoreCase("NonExistent")).thenReturn(Collections.emptyList());
        List<ChecklistItem> items = service.searchItems("NonExistent");
        assertTrue(items.isEmpty());
    }

    @Test
    void testCreateItemWithLongTitle() {
        ChecklistItem longTitleItem = new ChecklistItem(null, "A".repeat(300), "Description", false, 0);
        when(repository.save(any(ChecklistItem.class))).thenReturn(longTitleItem);
        ChecklistItem savedItem = service.createItem(longTitleItem);
        assertNotNull(savedItem);
        assertEquals(300, savedItem.getTitle().length());
    }

    @Test
    void testUpdateItemWithLongTitle() {
        when(repository.findById(1L)).thenReturn(Optional.of(checklistItem));
        when(repository.save(any(ChecklistItem.class))).thenReturn(checklistItem);

        ChecklistItem updatedItem = new ChecklistItem(1L, "A".repeat(300), "Updated Desc", true, 1);
        ChecklistItem result = service.updateItem(1L, updatedItem);

        assertEquals(300, result.getTitle().length());
    }

    @Test
    void testCreateItemWithNullTitle() {
        ChecklistItem nullTitleItem = new ChecklistItem(null, null, "Description", false, 0);
        when(repository.save(any(ChecklistItem.class))).thenReturn(nullTitleItem);
        ChecklistItem savedItem = service.createItem(nullTitleItem);
        assertNotNull(savedItem);
        assertNull(savedItem.getTitle());
    }
    @Test
    void testUpdateItemWithNullTitle() {
        when(repository.findById(1L)).thenReturn(Optional.of(checklistItem));
        when(repository.save(any(ChecklistItem.class))).thenReturn(checklistItem);

        ChecklistItem updatedItem = new ChecklistItem(1L, null, "Updated Desc", true, 1);
        ChecklistItem result = service.updateItem(1L, updatedItem);

        // Ensure the title remains unchanged
        assertEquals("Test Task", result.getTitle(), "Title should remain unchanged if null is provided");
        assertEquals("Updated Desc", result.getDescription(), "Description should update");
        assertTrue(result.isCompleted(), "Completed status should be updated");
    }


}
