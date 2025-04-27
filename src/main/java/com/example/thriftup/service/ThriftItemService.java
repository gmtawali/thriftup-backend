package com.example.thriftup.service;

import com.example.thriftup.model.ThriftItem;
import com.example.thriftup.repository.ThriftItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
import com.example.thriftup.specification.ThriftItemSpecification;

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
    public List<ThriftItem> filterItems(String category, BigDecimal maxPrice, 
                                   String location, Set<String> tags) {
    
    Specification<ThriftItem> spec = Specification.where(
        ThriftItemSpecification.hasCategory(category))
        .and(ThriftItemSpecification.hasPriceLessThanEqual(maxPrice))
        .and(ThriftItemSpecification.hasLocation(location))
        .and(ThriftItemSpecification.hasTags(tags));

    return repository.findAll(spec);
    }
}
