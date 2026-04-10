package com.example.expense_tracker.utils;

import com.example.expense_tracker.dto.categories.CategoriesResponseDto;
import com.example.expense_tracker.entity.Categories;
import org.springframework.stereotype.Component;

@Component
public class CategoriesMapper {
    public CategoriesResponseDto toCategoriesResponseDto(Categories category) {
        return CategoriesResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .build();
    }
}
