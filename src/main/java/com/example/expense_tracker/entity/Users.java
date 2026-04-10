package com.example.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "unique_name_email", columnNames = {"name", "email"})
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Expenses> expenses;

    @OneToMany(mappedBy = "user")
    private List<Budgets> budgets;


}
