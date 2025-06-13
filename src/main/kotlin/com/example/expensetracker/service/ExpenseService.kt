package com.example.expensetracker.service

import com.example.expensetracker.domain.expense.Expense
import com.example.expensetracker.domain.expense.ExpenseRepository
import com.example.expensetracker.domain.user.User
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository
) {

    fun addExpense(
        user: User,
        amount: Int,
        category: String,
        description: String?,
        date: LocalDate
    ): Expense {
        val expense = Expense(
            user = user,
            amount = amount,
            category = category,
            description = description,
            date = date
        )
        return expenseRepository.save(expense)
    }

    fun getExpensesByUser(user: User): List<Expense> {
        return expenseRepository.findAllByUser(user)
    }

    fun getExpensesforMonth(
        user: User,
        year: Int,
        month: Int
    ): List<Expense> {
        val start = LocalDate.of(year, month, 1)
        val end = start.plusMonths(1).minusDays(1)
        return expenseRepository.findAllByUserAndDateBetween(user, start, end)
    }

    fun deleteexpense(id: Long) {
        expenseRepository.deleteById(id)
    }
}