package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.expense.ExpenseRequestDto;
import com.example.expense_tracker.dto.expense.ExpenseResponseDto;
import com.example.expense_tracker.service.ExpensesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpensesController {
    private final ExpensesService expensesService;

    @PostMapping("/expenseCreation/{userId}")
    public ResponseEntity<ExpenseResponseDto> createExpense(@RequestBody @Valid ExpenseRequestDto expenseRequestDto, @PathVariable Long userId) {
        return new ResponseEntity<>(
                expensesService.createExpense(expenseRequestDto, userId), HttpStatus.CREATED);
    }

    @GetMapping("/allExpenses")
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpenses() {
        return ResponseEntity.ok(expensesService.getAllExpenses());
    }

    @GetMapping("/userExpense/{userId}")
    public ResponseEntity<List<ExpenseResponseDto>> getUserExpense(@PathVariable Long userId) {
        return ResponseEntity.ok(expensesService.getUserExpense(userId));
    }
}
