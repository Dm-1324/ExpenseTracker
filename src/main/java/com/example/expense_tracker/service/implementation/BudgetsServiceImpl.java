package com.example.expense_tracker.service.implementation;

import com.example.expense_tracker.dto.budget.BudgetResponseDto;
import com.example.expense_tracker.entity.Budgets;
import com.example.expense_tracker.entity.Categories;
import com.example.expense_tracker.entity.Users;
import com.example.expense_tracker.exception.ResourceNotFoundException;
import com.example.expense_tracker.repository.BudgetsRepository;
import com.example.expense_tracker.repository.CategoriesRepository;
import com.example.expense_tracker.repository.UsersRepository;
import com.example.expense_tracker.service.BudgetsService;
import com.example.expense_tracker.utils.BudgetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetsServiceImpl implements BudgetsService {

    private final BudgetsRepository budgetsRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;
    private final BudgetMapper budgetMapper;

    @Override
    public BudgetResponseDto createOrUpdateBudget(Long userId, Long categoryId, Double limit, Integer month, Integer year) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Categories category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Budgets budget = budgetsRepository
                .findByUserIdAndCategoryIdAndMonthAndYear(userId, categoryId, month, year)
                .orElseGet(() -> Budgets.builder()
                        .user(user)
                        .category(category)
                        .month(month)
                        .year(year)
                        .build());
        budget.setMonthlyLimit(limit);
        Budgets savedBudget = budgetsRepository.save(budget);
        return budgetMapper.toBudgetResponseDto(savedBudget, userId);
    }

    @Override
    public List<BudgetResponseDto> getUserBudgets(Long userId, Integer month, Integer year) {

        List<Budgets> budgets = budgetsRepository
                .findByUserIdAndMonthAndYear(userId, month, year);

        return budgets.stream()
                .map(budget ->
                        budgetMapper.toBudgetResponseDto(budget, userId))
                .toList();
    }

    @Override
    public void deleteBudget(Long budgetId, Long userId) {
        Budgets budget = budgetsRepository
                .findByIdAndUserId(budgetId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        budgetsRepository.delete(budget);
    }
}
