package com.example.expensetracker.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Configuration
class CorsConfig {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")  // 모든 경로
                    .allowedOrigins("http://localhost:5173") // 프론트엔드 포트
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")    // 모든 헤더 허용
                    .exposedHeaders("Authorization") // 응답 헤더에 Authorization 노출
                    .allowCredentials(true) // 인증 정보 포함 허용 (토큰 등)
            }
        }
    }
}