package com.example.thriftup.controller;

import com.example.thriftup.model.ThriftItem;
import com.example.thriftup.service.ThriftItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/items")
public class ThriftItemController {
    private final ThriftItemService service;
    
    public ThriftItemController(ThriftItemService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ThriftItem> addItem(@RequestBody ThriftItem item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addItem(item));
    }
    
    @GetMapping
    public ResponseEntity<List<ThriftItem>> getItems(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Set<String> tags) {
        
        return ResponseEntity.ok(service.filterItems(category, maxPrice, location, tags));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ThriftItem> getItemById(@PathVariable Long id) {
        ThriftItem item = service.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ThriftItem> updateItem(@PathVariable Long id, @RequestBody ThriftItem item) {
        ThriftItem updatedItem = service.updateItem(id, item);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
