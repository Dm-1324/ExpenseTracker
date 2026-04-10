package com.example.expense_tracker.dto.budget;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetResponseDto {

    private String category;
    private Double limit;
    private Double spent;
    private Double remainingAmount;
}
