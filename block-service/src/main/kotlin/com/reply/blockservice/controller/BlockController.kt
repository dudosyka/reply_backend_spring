package com.reply.blockservice.controller

import com.reply.block.request.TestRequestDto
import com.reply.block.response.TestResponseDto
import com.reply.blockservice.service.BlockService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/block")
class BlockController (
    val blockService: BlockService
) {
    @PostMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    fun test(@RequestBody request: TestRequestDto): TestResponseDto {
        return TestResponseDto(blockService.testMethod(request))
    }
}