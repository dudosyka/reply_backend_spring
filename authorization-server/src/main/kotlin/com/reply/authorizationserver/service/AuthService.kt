package com.reply.authorizationserver.service

import com.reply.authorizationserver.model.UserModel
import com.reply.authorizationserver.repository.UserRepository
import com.reply.dto.auth.request.AuthDto
import com.reply.dto.auth.request.RegistrationDto
import com.reply.dto.auth.response.RegistrationOutputDto
import com.reply.dto.auth.response.TokenOutputDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AuthService(
    val jwtEncoder: JwtEncoder,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {
    fun login(data: AuthDto): ResponseEntity<TokenOutputDto> {
        return try {
            val user = userRepository.findByLogin(data.login)
            if (!passwordEncoder.matches(data.password, user?.password ?: "")) {
                throw BadCredentialsException("Bad Credentials")
            }

            val now = Instant.now()
            val expiry = 36000L

            println(user!!.roles)

//            val scope = user.roles.joinToString(" ") { it.name }
            val scope = "block.read"
            val claims =
                JwtClaimsSet.builder().claim("roles", scope).issuer("https://psyreply.com").issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry)).subject(user.username).claim("userId", user.id).build()

            val token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue

            ResponseEntity.ok().body(TokenOutputDto(token))
        } catch (e: Exception) {
            println(e)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    fun registration(data: RegistrationDto): ResponseEntity<RegistrationOutputDto> {
        userRepository.save(UserModel(data.name, passwordEncoder.encode(data.password), mutableListOf(), 0))

        return ResponseEntity.ok().body(RegistrationOutputDto(0, true))
    }
}