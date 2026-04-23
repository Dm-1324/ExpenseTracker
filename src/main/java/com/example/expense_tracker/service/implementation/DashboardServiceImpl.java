package com.example.expense_tracker.service.implementation;

import com.example.expense_tracker.dto.dashboard.DashboardSummaryResponseDto;
import com.example.expense_tracker.entity.Categories;
import com.example.expense_tracker.exception.ResourceNotFoundException;
import com.example.expense_tracker.repository.CategoriesRepository;
import com.example.expense_tracker.repository.ExpensesRepository;
import com.example.expense_tracker.repository.UsersRepository;
import com.example.expense_tracker.service.DashboardService;
import com.example.expense_tracker.utils.DashboardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ExpensesRepository expensesRepository;
    private final CategoriesRepository categoriesRepository;
    private final UsersRepository usersRepository;
    private final DashboardMapper dashboardMapper;

    @Override
    public DashboardSummaryResponseDto getMonthlySummary(Long userId, Integer month, Integer year) {
        if (!usersRepository.existsById(userId)) {
            throw new ResourceNotFoundException("USer not found with id " + userId);
        }

        Double totalMonthlySpent = expensesRepository.getTotalMonthlySpent(userId, month, year);

        List<Categories> categories = categoriesRepository.findByUserId(userId);

        return dashboardMapper.toDashboardSummaryResponseDto(totalMonthlySpent, categories);
    }
}
