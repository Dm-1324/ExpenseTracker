package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Expenses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    Optional<Expenses> findByUserId(Long userId);

    @Query("SELECT e FROM Expenses e WHERE e.user.id = :userId " +
            "AND MONTH(e.date) = :month AND YEAR(e.date) = :year")
    Page<Expenses> findByUserIdAndMonthAndYear(Long userId, Integer month, Integer year, Pageable pageable);
}