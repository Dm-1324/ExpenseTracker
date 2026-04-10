package com.example.expense_tracker.dto.expense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseUpdateDto {
    private Double amount;
    private String description;
    private LocalDateTime expenseDate;
}
