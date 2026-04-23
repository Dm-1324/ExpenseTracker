package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.dashboard.DashboardSummaryResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface DashboardService {
    DashboardSummaryResponseDto getMonthlySummary(Long userId, Integer month, Integer year);
}
