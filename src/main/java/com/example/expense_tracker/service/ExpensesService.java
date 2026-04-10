package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.expense.ExpenseRequestDto;
import com.example.expense_tracker.dto.expense.ExpenseResponseDto;
import com.example.expense_tracker.dto.expense.ExpenseUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpensesService {
    ExpenseResponseDto createExpense(ExpenseRequestDto expenseRequestDto, Long userId);

    List<ExpenseResponseDto> getAllExpenses();

    List<ExpenseResponseDto> getUserExpense(Long userId);

    ExpenseResponseDto updateExpense(ExpenseUpdateDto expenseUpdateDto, Long expenseId, Long userId);
}
