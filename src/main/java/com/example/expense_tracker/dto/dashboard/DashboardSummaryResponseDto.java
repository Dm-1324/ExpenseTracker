package com.example.expense_tracker.dto.dashboard;

import com.example.expense_tracker.entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardSummaryResponseDto {
    private Double totalSpent;
    private List<Categories> categoriesList;
}
