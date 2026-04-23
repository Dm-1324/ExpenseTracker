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
    private Long id;
    private Long categoryId;
    private String categoryName;
    private Double limit;
    private Double spent;
    private Double remainingAmount;
    private Integer month;
    private Integer year;
}