package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.user.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {

    UserResponseDto getUserById(Long userId);

}
