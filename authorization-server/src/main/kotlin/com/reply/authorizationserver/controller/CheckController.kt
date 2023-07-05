package com.reply.authorizationserver.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/check/token")
class CheckController {

    @GetMapping
    fun checkAuthority(auth: Authentication) {
        println(auth)
        println(auth.name)
//        val jwt = (auth.principal as Jwt)
//        println(jwt.claims.entries)
//        println(jwt.id)
//        println(jwt.subject)
//        println(jwt.audience)
        ResponseEntity.ok().body(auth)
    }
}