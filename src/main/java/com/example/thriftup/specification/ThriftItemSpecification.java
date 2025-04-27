package com.example.thriftup.specification;

import com.example.thriftup.model.ThriftItem;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Set;

public class ThriftItemSpecification {

    public static Specification<ThriftItem> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("category"), category);
        };
    }

    public static Specification<ThriftItem> hasPriceLessThanEqual(BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (maxPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqual(root.get("price"), maxPrice);
        };
    }

    public static Specification<ThriftItem> hasLocation(String location) {
        return (root, query, criteriaBuilder) -> {
            if (location == null || location.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("location"), location);
        };
    }

    public static Specification<ThriftItem> hasTags(Set<String> tags) {
        return (root, query, criteriaBuilder) -> {
            if (tags == null || tags.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.join("tags").in(tags);
        };
    }
}