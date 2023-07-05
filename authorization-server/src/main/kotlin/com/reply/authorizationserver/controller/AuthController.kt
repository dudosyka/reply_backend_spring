package com.reply.authorizationserver.controller

import com.reply.authorizationserver.service.AuthService
import com.reply.dto.auth.request.AuthDto
import com.reply.dto.auth.request.RegistrationDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(val service: AuthService) {

    @PostMapping("/login")
    fun login(
        @RequestBody data: AuthDto
    ) = service.login(data)

    @PostMapping("/reg")
    fun login(
        @RequestBody data: RegistrationDto
    ) = service.registration(data)
}