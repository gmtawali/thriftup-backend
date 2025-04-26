package com.example.thriftup.service;

import com.example.thriftup.model.ThriftItem;
import com.example.thriftup.repository.ThriftItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ThriftItemService {
    private final ThriftItemRepository repository;
    
    public ThriftItemService(ThriftItemRepository repository) {
        this.repository = repository;
    }
    
    public ThriftItem addItem(ThriftItem item) {
        return repository.save(item);
    }
    
    public List<ThriftItem> getAllItems() {
        return repository.findAll();
    }
    
    public ThriftItem getItemById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    public List<ThriftItem> filterItems(String category, BigDecimal maxPrice, 
                                       String location, Set<String> tags) {
        if (category != null && !category.isEmpty()) {
            return repository.findByCategory(category);
        } else if (maxPrice != null) {
            return repository.findByPriceLessThanEqual(maxPrice);
        } else if (location != null && !location.isEmpty()) {
            return repository.findByLocation(location);
        } else if (tags != null && !tags.isEmpty()) {
            return repository.findByTagsContaining(tags);
        }
        
        return getAllItems();
    }
    
    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
    
    public ThriftItem updateItem(Long id, ThriftItem updatedItem) {
        if (repository.existsById(id)) {
            updatedItem.setId(id);
            return repository.save(updatedItem);
        }
        return null;
    }
}
