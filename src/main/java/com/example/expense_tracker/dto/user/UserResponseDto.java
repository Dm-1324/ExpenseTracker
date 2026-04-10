package com.example.expense_tracker.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
