package com.example.expense_tracker.service.implementation;

import com.example.expense_tracker.dto.categories.CategoriesResponseDto;
import com.example.expense_tracker.repository.CategoriesRepository;
import com.example.expense_tracker.service.CategoriesService;
import com.example.expense_tracker.utils.CategoriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final CategoriesMapper categoriesMapper;

    @Override
    public List<CategoriesResponseDto> getAllCategories() {
        return categoriesRepository.findAll()
                .stream().map(categoriesMapper::toCategoriesResponseDto).toList();
    }
}
