package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Expenses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    Optional<Expenses> findByUserId(Long userId);

    @Query("SELECT e FROM Expenses e WHERE e.user.id = :userId " +
            "AND (:month IS NULL OR MONTH(e.date) = :month) " +
            "AND (:year IS NULL OR YEAR(e.date) = :year)")
    Page<Expenses> findByUserIdAndMonthAndYear(
            @Param("userId") Long userId,
            @Param("month") Integer month,
            @Param("year") Integer year,
            Pageable pageable);

    @Query("""
                SELECT COALESCE(SUM(e.amount), 0)
                FROM Expenses e
                WHERE e.user.id = :userId
                AND e.category.id = :categoryId
                AND MONTH(e.date) = :month
                AND YEAR(e.date) = :year
            """)
    Double getTotalSpentByCategory(
            @Param("userId") Long userId,
            @Param("categoryId") Long categoryId,
            @Param("month") Integer month,
            @Param("year") Integer year
    );


    @Query("""
                SELECT COALESCE(SUM(e.amount), 0)
                FROM Expenses e
                WHERE e.user.id = :userId
                AND MONTH(e.date) = :month
                AND YEAR(e.date) = :year
            """)
    Double getTotalMonthlySpent(
            @Param("userId") Long userId,
            @Param("month") Integer month,
            @Param("year") Integer year
    );
}