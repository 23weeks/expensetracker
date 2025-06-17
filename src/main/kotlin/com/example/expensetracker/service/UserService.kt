package com.example.expensetracker.service

import com.example.expensetracker.domain.user.User
import com.example.expensetracker.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.security.crypto.password.PasswordEncoder

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    fun createUser(username: String, email: String, password: String): User {
        // Todo: 비밀번호 암호화는 추후에 추가
        if (userRepository.existsByUsername(username)) {
            throw IllegalArgumentException("이미 사용 중인 아이디입니다.")
        }
        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("이미 등록된 이메일입니다.")
        }

        val encodedPassword = passwordEncoder.encode(password)
        val user = User(
            username = username,
            email = email,
            password = encodedPassword
        )
        return userRepository.save(user)
    }
}