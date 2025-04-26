package com.example.thriftup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "thrift_items")
@Data
@NoArgsConstructor
public class ThriftItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String title;
    
    private BigDecimal price;
    
    private String category;
    
    private String location;
    
    @ElementCollection
    private Set<String> tags;
    
    private String link;
    
    private String imageUrl;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}
