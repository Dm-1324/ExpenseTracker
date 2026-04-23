package com.example.expense_tracker.utils;

import com.example.expense_tracker.dto.budget.BudgetResponseDto;
import com.example.expense_tracker.entity.Budgets;
import com.example.expense_tracker.repository.ExpensesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BudgetMapper {

    private final ExpensesRepository expensesRepository;

    public BudgetResponseDto toBudgetResponseDto(Budgets budget, Long userId) {
        Double spent = expensesRepository.getTotalSpentByCategory(
                userId,
                budget.getCategory().getId(),
                budget.getMonth(),
                budget.getYear()
        );

        Double remaining = budget.getMonthlyLimit() - spent;

        return BudgetResponseDto.builder()
                .id(budget.getId())
                .categoryId(budget.getCategory().getId())
                .categoryName(budget.getCategory().getName())
                .limit(budget.getMonthlyLimit())
                .spent(spent)
                .remainingAmount(remaining)
                .month(budget.getMonth())
                .year(budget.getYear())
                .build();
    }
}
