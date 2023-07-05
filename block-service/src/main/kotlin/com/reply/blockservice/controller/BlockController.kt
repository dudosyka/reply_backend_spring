package com.reply.blockservice.controller

import com.reply.blockservice.service.BlockService
import com.reply.dto.block.request.TestRequestDto
import com.reply.dto.block.response.TestResponseDto
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/block")
class BlockController(
    val blockService: BlockService
) {

    @PreAuthorize("hasAuthority('ROLE_block.read')")
    @PostMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    fun test(@RequestBody request: TestRequestDto, auth: Authentication): TestResponseDto {
        println(auth)
        return TestResponseDto(blockService.testMethod(request, auth))
    }
}