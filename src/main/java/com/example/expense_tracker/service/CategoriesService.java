package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.categories.CategoriesResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriesService {

    List<CategoriesResponseDto> getAllCategories();
}
