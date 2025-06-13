package com.example.expensetracker.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable()}
            .headers { it.frameOptions() { frame -> frame.sameOrigin() } }
            .authorizeHttpRequests{
                it
                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { it.defaultSuccessUrl("/h2-console", true) }
        return http.build()
    }
}