package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Budgets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetsRepository extends JpaRepository<Budgets, Long> {

    Optional<Budgets> findByUserIdAndCategoryIdAndMonthAndYear(
            Long userId, Long categoryId, Integer month, Integer year
    );

    List<Budgets> findByUserIdAndMonthAndYear(
            Long userId, Integer month, Integer year
    );

    Optional<Budgets> findByIdAndUserId(Long id, Long userId);
}