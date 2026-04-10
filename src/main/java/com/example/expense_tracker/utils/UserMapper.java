package com.example.expense_tracker.utils;

import com.example.expense_tracker.dto.user.UserResponseDto;
import com.example.expense_tracker.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toUserResponseDto(Users users) {
        return UserResponseDto.builder()
                .id(users.getId())
                .name(users.getName())
                .email(users.getEmail())
                .createdAt(users.getCreatedAt())
                .build();
    }

}
