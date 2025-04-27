package com.example.thriftup.repository;

import com.example.thriftup.model.ThriftItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface ThriftItemRepository extends JpaRepository<ThriftItem, Long>,
                                        JpaSpecificationExecutor<ThriftItem> {
    List<ThriftItem> findByCategory(String category);
    List<ThriftItem> findByPriceLessThanEqual(BigDecimal maxPrice);
    List<ThriftItem> findByLocation(String location);
    List<ThriftItem> findByCreatedAtBefore(LocalDateTime date);
    
    @Query("SELECT t FROM ThriftItem t JOIN t.tags tag WHERE tag IN :tags")
    List<ThriftItem> findByTagsContaining(Set<String> tags);
}
