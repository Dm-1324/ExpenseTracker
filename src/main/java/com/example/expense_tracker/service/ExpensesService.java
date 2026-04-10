package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.expense.ExpenseRequestDto;
import com.example.expense_tracker.dto.expense.ExpenseResponseDto;
import com.example.expense_tracker.dto.expense.ExpenseUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpensesService {
    ExpenseResponseDto createExpense(ExpenseRequestDto expenseRequestDto, Long userId);

    List<ExpenseResponseDto> getAllExpenses();

    List<ExpenseResponseDto> getUserExpense(Long userId);

    Page<ExpenseResponseDto> getExpenses(Long userId, Integer month, Integer year, Pageable pageable);

    ExpenseResponseDto updateExpense(ExpenseUpdateDto expenseUpdateDto, Long expenseId, Long userId);

    void deleteExpense(Long expenseId, Long userId);
}
