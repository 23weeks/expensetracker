package com.example.expensetracker.controller

import com.example.expensetracker.domain.user.User 
import com.example.expensetracker.service.UserService
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseEntity<User> {
        val user = userService.createUser(
            username = request.username,
            email = request.email,
            password = request.password
        )
        return ResponseEntity.ok(user)
    }

    data class SignupRequest(
        val username: String,
        val email: String,
        val password: String
    )
}