package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
}