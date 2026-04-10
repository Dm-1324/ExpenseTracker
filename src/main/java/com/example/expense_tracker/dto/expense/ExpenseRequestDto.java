package com.example.expense_tracker.dto.expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequestDto {

    @NotNull(message = "Amount is Required")
    private Double amount;

    @NotNull(message = "Category id is Required")
    private Long categoryId;

    @NotBlank(message = "Description is Required")
    private String description;

    @NotNull(message = "Date of expense is Required")
    private LocalDateTime expenseDate;
}