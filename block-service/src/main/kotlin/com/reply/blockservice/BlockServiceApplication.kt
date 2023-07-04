package com.reply.blockservice

import com.reply.blockservice.config.JwtAuthorizationProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@EnableMethodSecurity
@SpringBootApplication
class BlockServiceApplication(
    val jwtAuthorizationProvider: JwtAuthorizationProvider
)