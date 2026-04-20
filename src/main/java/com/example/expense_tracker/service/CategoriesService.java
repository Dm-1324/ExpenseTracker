package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.categories.CategoriesResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriesService {

    List<CategoriesResponseDto> getAllCategories();

    CategoriesResponseDto createCategory(String name, Long userId);

    List<CategoriesResponseDto> getAllUserCategories(Long userId);

    void deleteCategory(Long categoryId, Long userId);

    CategoriesResponseDto getCategoryById(Long categoryId, Long userId);
}
