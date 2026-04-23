package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.budget.BudgetResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BudgetsService {
    BudgetResponseDto createOrUpdateBudget(Long userId, Long categoryId, Double limit, Integer month, Integer year);

    List<BudgetResponseDto> getUserBudgets(Long userId, Integer month, Integer year);

    void deleteBudget(Long budgetId, Long userId);
}
