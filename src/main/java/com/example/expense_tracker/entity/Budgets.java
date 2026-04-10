package com.example.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "budgets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Budgets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories category;

    private Double monthlyLimit;
}
