package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.budget.BudgetResponseDto;
import com.example.expense_tracker.service.BudgetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetsController {

    private final BudgetsService budgetsService;

    @PostMapping("/createOrUpdate/user/{userId}/category/{categoryId}/limit/{limit}/month/{month}/year/{year}")
    public ResponseEntity<BudgetResponseDto> createOrUpdateBudget(
            @PathVariable Long userId,
            @PathVariable Long categoryId,
            @PathVariable Double limit,
            @PathVariable Integer month,
            @PathVariable Integer year) {
        return ResponseEntity.ok(budgetsService.createOrUpdateBudget(userId, categoryId, limit, month, year));
    }

    @GetMapping("/userBudgets/{userId}/month/{month}/year/{year}")
    public ResponseEntity<List<BudgetResponseDto>> getUserBudgets(
            @PathVariable Long userId,
            @PathVariable Integer month,
            @PathVariable Integer year) {
        return ResponseEntity.ok(budgetsService.getUserBudgets(userId, month, year));
    }

    @DeleteMapping("/deleteBudget/{budgetId}/user/{userId}")
    public ResponseEntity<String> deleteBudget(
            @PathVariable Long budgetId,
            @PathVariable Long userId) {
        budgetsService.deleteBudget(budgetId, userId);
        return new ResponseEntity<>("Budget deleted successfully", HttpStatus.OK);
    }
}