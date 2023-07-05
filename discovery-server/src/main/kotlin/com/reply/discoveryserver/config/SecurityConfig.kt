package com.reply.discoveryserver.config

import com.reply.security.PermitAllConfig
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityConfig : PermitAllConfig()