package com.reply.blockservice.exchange

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

class TestServiceExchanger(
    private val webClient: WebClient.Builder,
    private val token: String
) {
    fun getTest(data: String): String {
        return webClient.apply {
            it.defaultHeaders { headers ->
                headers.set(
                    "Authorization",
                    "Bearer $token"
                )
            }
        }
            .build().get()
            .uri("http://test-service/api/test/reset$data")
//            {
//                it.queryParam("test", data).build()
//            }
            .retrieve()
            .bodyToMono<String>()
            .block() ?: "Failed to retrieve TestService"
    }
}