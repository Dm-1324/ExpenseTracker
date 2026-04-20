package com.example.expense_tracker.service.implementation;

import com.example.expense_tracker.dto.categories.CategoriesResponseDto;
import com.example.expense_tracker.entity.Categories;
import com.example.expense_tracker.entity.Users;
import com.example.expense_tracker.enums.Type;
import com.example.expense_tracker.exception.NotAllowedException;
import com.example.expense_tracker.exception.ResourceNotFoundException;
import com.example.expense_tracker.repository.CategoriesRepository;
import com.example.expense_tracker.repository.UsersRepository;
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
    private final UsersRepository usersRepository;

    @Override
    public List<CategoriesResponseDto> getAllCategories() {
        return categoriesRepository.findAll()
                .stream().map(categoriesMapper::toCategoriesResponseDto).toList();
    }

    @Override
    public CategoriesResponseDto createCategory(String name, Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No user found with id " + userId));

        Categories category = Categories.builder()
                .name(name)
                .user(user)
                .type(Type.CUSTOM)
                .build();

        Categories savedCategory = categoriesRepository.save(category);
        return categoriesMapper.toCategoriesResponseDto(savedCategory);
    }

    @Override
    public List<CategoriesResponseDto> getAllUserCategories(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No user found with id " + userId));
        List<Categories> categories = categoriesRepository.findByUserId(userId);
        return categories.stream().map(categoriesMapper::toCategoriesResponseDto).toList();
    }

    @Override
    public void deleteCategory(Long categoryId, Long userId) {
        Categories category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));

        if (!category.getUser().getId().equals(userId)) {
            throw new NotAllowedException("You are not authorised to update this category");
        }
        if (!category.getExpenses().isEmpty()) {
            throw new NotAllowedException("Cannot delete category because it contains existing expenses. Please delete or reassign the expenses first.");
        }
        categoriesRepository.delete(category);
    }

    @Override
    public CategoriesResponseDto getCategoryById(Long categoryId, Long userId) {
        Categories category = categoriesRepository.findByIdAndUserId(categoryId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("No category found"));
        return categoriesMapper.toCategoriesResponseDto(category);
    }
}
