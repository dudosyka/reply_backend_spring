package com.reply.testservice.config

import com.reply.security.JwtAuthentication
import org.springframework.context.annotation.Configuration

@Configuration
class JwtAuthorizationProvider : JwtAuthentication()