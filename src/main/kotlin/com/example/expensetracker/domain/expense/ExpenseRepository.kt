package com.example.expensetracker.domain.expense

import com.example.expensetracker.domain.user.User
import com.example.expensetracker.domain.expense.Expense
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ExpenseRepository : JpaRepository<Expense, Long> {
    fun findAllByUser(user: User): List<Expense>
    fun findAllByUserAndDateBetween(
        user: User,
        start: LocalDate,
        end: LocalDate
    ): List<Expense>
}