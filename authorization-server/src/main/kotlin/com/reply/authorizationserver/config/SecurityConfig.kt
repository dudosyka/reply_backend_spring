package com.reply.authorizationserver.config

import com.nimbusds.jose.jwk.JWK
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import com.reply.authorizationserver.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler
import org.springframework.security.web.SecurityFilterChain
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@Configuration
class SecurityConfig(
    val userRepository: UserRepository,
    @Value("\${jwt.public_key}")
    val publicKey: RSAPublicKey,
    @Value("\${jwt.private_key}")
    val privateKey: RSAPrivateKey
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize.requestMatchers("/api/auth/**").permitAll()
                authorize.anyRequest().authenticated()
            }
            .csrf { csrf -> csrf.ignoringRequestMatchers("/api/auth/**") }
            .httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer { oauth -> oauth.jwt { it.jwtAuthenticationConverter(jwtAuthenticationConverter()) } }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling { exceptions ->
                exceptions
                    .authenticationEntryPoint(BearerTokenAuthenticationEntryPoint())
                    .accessDeniedHandler(BearerTokenAccessDeniedHandler())
            }
        return http.build()
    }

    @Bean
    fun users(): UserDetailsService {
        return UserDetailsService { username ->
            userRepository.findByLogin(username)
        }
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk: JWK = RSAKey.Builder(publicKey).privateKey(privateKey).build()
        val jwks: JWKSource<SecurityContext> = ImmutableJWKSet(JWKSet(jwk))
        return NimbusJwtEncoder(jwks)
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(publicKey).build()
    }

    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles")
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_")

        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter)

        return jwtAuthenticationConverter
    }
}