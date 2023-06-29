package com.reply.blockservice.service

import com.reply.block.request.TestRequestDto
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
@RequiredArgsConstructor
class BlockService(
    val webClient: WebClient.Builder
) {
    fun testMethod(request: TestRequestDto): String {
        val data = "${request.test}${request.testInt}"
        return webClient.build().get()
            .uri("http://test-service/api/test/$data")
//            {
//                it.queryParam("test", data).build()
//            }
            .retrieve()
            .bodyToMono<String>()
            .block() ?: "Failed to retrieve data from Service"
    }
}