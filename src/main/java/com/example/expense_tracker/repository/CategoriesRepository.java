package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Categories;
import com.example.expense_tracker.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    List<Categories> findByUserId(Long userId);

    Optional<Categories> findByIdAndUserId(Long categoryId, Long userId);

    List<Categories> findByUserIdOrType(Long userId, Type type);
}