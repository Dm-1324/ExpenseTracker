package com.example.expense_tracker.service.implementation;

import com.example.expense_tracker.dto.PaginatedResponseDto;
import com.example.expense_tracker.dto.expense.ExpenseRequestDto;
import com.example.expense_tracker.dto.expense.ExpenseResponseDto;
import com.example.expense_tracker.dto.expense.ExpenseUpdateDto;
import com.example.expense_tracker.entity.Expenses;
import com.example.expense_tracker.entity.Users;
import com.example.expense_tracker.exception.NotAllowedException;
import com.example.expense_tracker.exception.ResourceNotFoundException;
import com.example.expense_tracker.repository.ExpensesRepository;
import com.example.expense_tracker.repository.UsersRepository;
import com.example.expense_tracker.service.ExpensesService;
import com.example.expense_tracker.utils.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

        List<ExpenseResponseDto> userExpenseList = expensesRepository.findByUserId(userId).stream().map(expenseMapper
                ::toExpenseResponseDto).toList();

        return userExpenseList;
    }

    @Override
    public PaginatedResponseDto<ExpenseResponseDto> getExpenses(Long userId, Integer month, Integer year, Pageable pageable) {
        if (!usersRepository.existsById(userId)) {
            throw new ResourceNotFoundException("No user found with id " + userId);
        }

        Page<Expenses> expensePage = expensesRepository.findByUserIdAndMonthAndYear(userId, month, year, pageable);

        List<ExpenseResponseDto> content = expensePage.getContent()
                .stream()
                .map(expenseMapper::toExpenseResponseDto)
                .toList();

        return PaginatedResponseDto.<ExpenseResponseDto>builder()
                .content(content)
                .pageNumber(expensePage.getNumber())
                .pageSize(expensePage.getSize())
                .totalElements(expensePage.getTotalElements())
                .totalPages(expensePage.getTotalPages())
                .isLast(expensePage.isLast())
                .build();
    }

    @Override
    public ExpenseResponseDto updateExpense(ExpenseUpdateDto expenseUpdateDto, Long expenseId, Long userId) {
        Expenses expenses = expensesRepository.findById(expenseId).orElseThrow(
                () -> new ResourceNotFoundException("No expense found with id " + expenseId)
        );

        if (!Objects.equals(expenses.getUser().getId(), userId)) {
            throw new NotAllowedException("You are not authorised to update this expense");
        }
        return expenseMapper.updatingExpense(expenseUpdateDto, expenseId);
    }

    @Override
    public void deleteExpense(Long expenseId, Long userId) {
        Expenses expenses = expensesRepository.findById(expenseId).orElseThrow(
                () -> new ResourceNotFoundException("No expense found with id " + expenseId)
        );

        if (!Objects.equals(expenses.getUser().getId(), userId)) {
            throw new NotAllowedException("You are not authorised to update this expense");
        }
        expensesRepository.delete(expenses);
    }
}
