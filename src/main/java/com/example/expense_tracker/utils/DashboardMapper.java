package com.example.expense_tracker.utils;

import com.example.expense_tracker.dto.dashboard.DashboardSummaryResponseDto;
import com.example.expense_tracker.entity.Categories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardMapper {
    
    public DashboardSummaryResponseDto toDashboardSummaryResponseDto(Double totalSpent, List<Categories> categories) {
        return DashboardSummaryResponseDto.builder()
                .totalSpent(totalSpent != null ? totalSpent : 0.0)
                .categoriesList(categories)
                .build();
    }
}