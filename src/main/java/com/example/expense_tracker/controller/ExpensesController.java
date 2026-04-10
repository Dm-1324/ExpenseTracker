package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.PaginatedResponseDto;
import com.example.expense_tracker.dto.expense.ExpenseRequestDto;
import com.example.expense_tracker.dto.expense.ExpenseResponseDto;
import com.example.expense_tracker.dto.expense.ExpenseUpdateDto;
import com.example.expense_tracker.service.ExpensesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/{userId}/history")
    public ResponseEntity<PaginatedResponseDto<ExpenseResponseDto>> getExpenses(
            @PathVariable Long userId,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year,
            @ParameterObject Pageable pageable) {

        return ResponseEntity.ok(expensesService.getExpenses(userId, month, year, pageable));
    }

    @PatchMapping("/user/{userId}/updateExpense/{expenseId}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(@RequestBody ExpenseUpdateDto expenseUpdateDto, @PathVariable Long expenseId, @PathVariable Long userId) {
        return ResponseEntity.ok(expensesService.updateExpense(expenseUpdateDto, expenseId, userId));
    }

    @DeleteMapping("/user/{userId}/deleteExpense/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId, @PathVariable Long userId) {
        expensesService.deleteExpense(expenseId, userId);
        return ResponseEntity.ok("Expense Deleted Successfully");
    }
}
