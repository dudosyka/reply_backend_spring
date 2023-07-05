package com.reply.testservice.controller

import com.reply.dto.test.request.TestRequestDto
import com.reply.dto.test.response.TestResponseDto
import com.reply.testservice.service.TestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/test")
class TestController(
    val testService: TestService
) {
    @GetMapping("{test}")
    fun test(@PathVariable("test") test: String): TestResponseDto {
        return TestResponseDto(testService.testMethod(TestRequestDto(test)))
    }
}