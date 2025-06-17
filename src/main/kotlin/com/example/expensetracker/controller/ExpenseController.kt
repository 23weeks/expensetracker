package com.example.expensetracker.controller

import com.example.expensetracker.domain.user.User
import com.example.expensetracker.domain.expense.Expense
import com.example.expensetracker.service.ExpenseService
import com.example.expensetracker.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/expenses")
class ExpenseController(
    private val expenseService: ExpenseService,
    private val userService: UserService
) {
    
    @GetMapping
    fun getExpenses(principal: Principal): ResponseEntity<List<Expense>> {
        val user = userService.findByUsername(principal.name) ?: return ResponseEntity.status(401).build()
        val expenses =  expenseService.getExpensesByUser(user)
        return ResponseEntity.ok(expenses)
    }

/*
    fun addExpense(expense: Expense) {
        // Logic to add a new expense
    }

    fun updateExpense(expenseId: String, updatedExpense: Expense) {
        // Logic to update an existing expense
    }

    fun deleteExpense(expenseId: String) {
        // Logic to delete an expense
    }
*/
}