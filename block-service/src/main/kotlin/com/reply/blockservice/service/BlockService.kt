package com.reply.blockservice.service

import com.reply.blockservice.exchange.TestServiceExchanger
import com.reply.dto.block.request.TestRequestDto
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class BlockService(
    val webClient: WebClient.Builder
) {
    fun testMethod(request: TestRequestDto, authentication: Authentication): String {
        val data = "${request.test}${request.testInt}"
        val testServiceExchanger =
            TestServiceExchanger(webClient, (authentication as JwtAuthenticationToken).token.tokenValue)
        return testServiceExchanger.getTest(data)
    }
}