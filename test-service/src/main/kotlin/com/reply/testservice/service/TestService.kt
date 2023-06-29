package com.reply.testservice.service

import com.reply.test.request.TestRequestDto
import org.springframework.stereotype.Service

@Service
class TestService {
    fun testMethod(request: TestRequestDto): String {
        return request.stringInput.reversed()
    }
}