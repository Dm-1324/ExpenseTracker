package com.example.expense_tracker.utils;

import com.example.expense_tracker.dto.expense.ExpenseRequestDto;
import com.example.expense_tracker.dto.expense.ExpenseResponseDto;
import com.example.expense_tracker.dto.expense.ExpenseUpdateDto;
import com.example.expense_tracker.entity.Expenses;
import com.example.expense_tracker.exception.ResourceNotFoundException;
import com.example.expense_tracker.repository.CategoriesRepository;
import com.example.expense_tracker.repository.ExpensesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseMapper {

    private final CategoriesRepository categoriesRepository;
    private final ExpensesRepository expensesRepository;

    public Expenses toExpenseEntity(ExpenseRequestDto expenseRequestDto) {
        return Expenses.builder()
                .amount(expenseRequestDto.getAmount())
                .category(
                        categoriesRepository.findById(expenseRequestDto.getCategoryId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "Category not found with id " + expenseRequestDto.getCategoryId()
                                )))
                .description(expenseRequestDto.getDescription())
                .date(expenseRequestDto.getExpenseDate())
                .build();
    }

    public ExpenseResponseDto toExpenseResponseDto(Expenses expense) {
        return ExpenseResponseDto.builder()
                .id(expense.getId())
                .amount(expense.getAmount())
                .categoryName(expense.getCategory() != null ?
                        expense.getCategory().getName() : "Not Defined")
                .description(expense.getDescription())
                .expenseDate(expense.getDate())
                .userId(expense.getUser() != null ? expense.getUser().getId() : null)
                .build();
    }

    public ExpenseResponseDto updatingExpense(ExpenseUpdateDto expenseUpdateDto, Long expenseId) {

        Expenses expense = expensesRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException(
                "No expense found with" +
                        " id " + expenseId));

        expense.setAmount(expenseUpdateDto.getAmount() != null ? expenseUpdateDto.getAmount() : expense.getAmount());
        expense.setDescription(expenseUpdateDto.getDescription() != null ? expenseUpdateDto.getDescription() : expense.getDescription());
        expense.setDate(expenseUpdateDto.getExpenseDate() != null ? expenseUpdateDto.getExpenseDate() : expense.getDate());

        expensesRepository.save(expense);

        return toExpenseResponseDto(expense);
    }
}
