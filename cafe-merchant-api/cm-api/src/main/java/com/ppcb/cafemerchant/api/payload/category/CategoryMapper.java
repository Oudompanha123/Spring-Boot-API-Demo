package com.ppcb.cafemerchant.api.payload.category;

import com.ppcb.cafemerchant.common.domain.category.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Component
public class CategoryMapper {
    public CategoryResponse toResponse(Category category) {
        return getCategoryResponse(category);
    }

    public static CategoryResponse getCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .imageUrl(category.getIconUrl())
                .createdAt(category.getCreatedAt() != null ?
                        LocalDate.from(LocalDateTime.ofInstant(category.getCreatedAt(), ZoneOffset.UTC)) : null)
                .updatedAt(category.getUpdatedAt() != null ?
                        LocalDate.from(LocalDateTime.ofInstant(category.getUpdatedAt(), ZoneOffset.UTC)) : null)
                .createdBy(category.getCreatedBy() != null ? category.getCreatedBy().getUsername() : null)
                .build();
    }
}
