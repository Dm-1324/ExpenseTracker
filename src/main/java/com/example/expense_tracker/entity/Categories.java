package com.example.expense_tracker.entity;

import com.example.expense_tracker.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "category")
    private List<Expenses> expenses;

    @OneToMany(mappedBy = "category")
    private List<Budgets> budgets;

}
