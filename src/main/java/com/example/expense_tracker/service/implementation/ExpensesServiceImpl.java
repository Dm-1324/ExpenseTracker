package com.example.expense_tracker.service.implementation;

import com.example.expense_tracker.dto.expense.ExpenseRequestDto;
import com.example.expense_tracker.dto.expense.ExpenseResponseDto;
import com.example.expense_tracker.dto.expense.ExpenseUpdateDto;
import com.example.expense_tracker.entity.Expenses;
import com.example.expense_tracker.entity.Users;
import com.example.expense_tracker.exception.ResourceNotFoundException;
import com.example.expense_tracker.repository.ExpensesRepository;
import com.example.expense_tracker.repository.UsersRepository;
import com.example.expense_tracker.service.ExpensesService;
import com.example.expense_tracker.utils.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final UsersRepository usersRepository;
    private final ExpenseMapper expenseMapper;

    @Override
    public ExpenseResponseDto createExpense(ExpenseRequestDto expenseRequestDto, Long userId) {
        Expenses expense = expenseMapper.toExpenseEntity(expenseRequestDto);
        Users user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + userId
                        ));
        expense.setUser(user);
        expensesRepository.save(expense);

        return expenseMapper.toExpenseResponseDto(expense);
    }

    @Override
    public List<ExpenseResponseDto> getAllExpenses() {
        return expensesRepository.findAll().stream().map(expenseMapper::toExpenseResponseDto).toList();
    }

    @Override
    public List<ExpenseResponseDto> getUserExpense(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + userId
                        ));

        List<ExpenseResponseDto> userExpenseList = expensesRepository.findAll()
                .stream()
                .filter(exp -> exp.getUser() == user)
                .map(expenseMapper::toExpenseResponseDto)
                .toList();

        return userExpenseList;
    }

    @Override
    public ExpenseResponseDto updateExpense(ExpenseUpdateDto expenseUpdateDto, Long expenseId, Long userId) {
        return null;
    }
}
