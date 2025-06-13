package com.example.expensetracker.service

import com.example.expensetracker.domain.user.User
import com.example.expensetracker.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    fun createUser(username: String, email: String, password: String): User {
        // Todo: 비밀번호 암호화는 추후에 추가
        if (userRepository.existsByUsername(username)) {
            throw IllegalArgumentException("이미 존재하는 사용자입니다.")
        }
        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("이미 존재하는 이메일입니다.")
        }

        val user = User(
            username = username,
            email = email,
            password = password
        )
        return userRepository.save(user)
    }
}