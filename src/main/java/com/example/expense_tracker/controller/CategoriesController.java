package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.categories.CategoriesResponseDto;
import com.example.expense_tracker.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("createCategory/{name}/user/{userId}")
    public ResponseEntity<CategoriesResponseDto> createCategory(@PathVariable String name, @PathVariable Long userId) {
        return ResponseEntity.ok(categoriesService.createCategory(name, userId));
    }

    @GetMapping("/userCategories/{userId}")
    public ResponseEntity<List<CategoriesResponseDto>> getAllUserCategories(@PathVariable Long userId) {
        return ResponseEntity.ok(categoriesService.getAllUserCategories(userId));
    }

    @DeleteMapping("/deleteCategory/{categoryId}/user/{userId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId, @PathVariable Long userId) {
        categoriesService.deleteCategory(categoryId, userId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/getCategory/{categoryId}/user/{userId}")
    public ResponseEntity<CategoriesResponseDto> getCategoryById(@PathVariable Long categoryId, @PathVariable Long userId) {
        return ResponseEntity.ok(categoriesService.getCategoryById(categoryId, userId));
    }
}
