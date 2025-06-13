package com.example.expensetracker.domain.expense

import com.example.expensetracker.domain.user.User
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "expenses")
data class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val amount: Int,

    @Column(nullable = false)
    val category: String,

    val description: String? = null,

    @Column(nullable = false)
    val date: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
)