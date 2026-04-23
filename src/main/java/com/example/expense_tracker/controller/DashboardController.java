package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.dashboard.DashboardSummaryResponseDto;
import com.example.expense_tracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary/user/{userId}/month/{month}/year/{year}")
    public ResponseEntity<DashboardSummaryResponseDto> getMonthlySummary(
            @PathVariable Long userId,
            @PathVariable Integer month,
            @PathVariable Integer year) {
        return ResponseEntity.ok(dashboardService.getMonthlySummary(userId, month, year));
    }
}