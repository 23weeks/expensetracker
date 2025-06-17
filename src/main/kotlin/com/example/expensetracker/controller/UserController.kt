package com.example.expensetracker.controller

import com.example.expensetracker.domain.user.User 
import com.example.expensetracker.service.UserService
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import com.example.expensetracker.security.JwtProvider

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider
) {

    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseEntity<User> {
        return try {
            val user = userService.createUser(request.username, request.email, request.password)
             ResponseEntity.ok(user)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Map<String, String>> {
        val user = userService.findByUsername(request.username)
        ?: return ResponseEntity.status(401).body(mapOf("error" to "사용자를 찾을 수 없습니다."))

        if (!passwordEncoder.matches(request.password, user.password)) {
            return ResponseEntity.status(401).body(mapOf("error" to "비밀번호가 일치하지 않습니다."))
        }

        val token = jwtProvider.generateToken(user.username)
        return ResponseEntity.ok(mapOf("token" to token))
    }

    data class SignupRequest(
        val username: String,
        val email: String,
        val password: String
    )

    data class LoginRequest(
        val username: String,
        val password: String
    )
}