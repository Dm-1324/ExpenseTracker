package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.categories.CategoriesResponseDto;
import com.example.expense_tracker.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<List<CategoriesResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoriesService.getAllCategories());
    }
}
