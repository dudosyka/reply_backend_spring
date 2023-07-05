package com.reply.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.stereotype.Component
import java.security.interfaces.RSAPublicKey

@Component
class JwtAuthentication :
    AuthenticationProvider {

    /**
     * Performs authentication with the same contract as
     * [org.springframework.security.authentication.AuthenticationManager.authenticate]
     * .
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * `null` if the `AuthenticationProvider` is unable to support
     * authentication of the passed `Authentication` object. In such a case,
     * the next `AuthenticationProvider` that supports the presented
     * `Authentication` class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    override fun authenticate(authentication: Authentication): Authentication {
        val jwt = authentication.principal as Jwt
        return UsernamePasswordAuthenticationToken(jwt.subject, jwt.subject, mutableListOf(RoleDto("block.read")))
    }

    /**
     * Returns `true` if this <Code>AuthenticationProvider</Code> supports the
     * indicated <Code>Authentication</Code> object.
     *
     *
     * Returning `true` does not guarantee an
     * `AuthenticationProvider` will be able to authenticate the presented
     * instance of the `Authentication` class. It simply indicates it can
     * support closer evaluation of it. An `AuthenticationProvider` can still
     * return `null` from the [.authenticate] method to
     * indicate another `AuthenticationProvider` should be tried.
     *
     *
     *
     * Selection of an `AuthenticationProvider` capable of performing
     * authentication is conducted at runtime the `ProviderManager`.
     *
     * @param authentication
     * @return `true` if the implementation can more closely evaluate the
     * `Authentication` class presented
     */
    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.equals(UsernamePasswordAuthenticationToken::class.java) ?: false
    }

    @Bean
    fun authenticationManager(
        http: HttpSecurity
    ): AuthenticationManager {
        val builder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        builder.authenticationProvider(this)
        return builder.build()
    }


    @Bean
    fun jwtDecoder(
        @Value("\${jwt.public_key}")
        publicKey: RSAPublicKey
    ): JwtDecoder {
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