package com.example.expense_tracker.dto.categories;

import com.example.expense_tracker.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesResponseDto {
    private Long id;
    private String name;
    private Type type;
}
