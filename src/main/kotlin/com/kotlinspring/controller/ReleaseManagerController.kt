package com.kotlinspring.controller

import com.kotlinspring.dto.MicroServiceDto
import com.kotlinspring.service.ReleaseManagerService
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/releaseManagement")
class ReleaseManagerController(val releaseManagerService: ReleaseManagerService) {

    companion object : KLogging()

    @GetMapping("/services")
    fun getServices(@RequestParam systemVersion: Int): List<MicroServiceDto> {
        logger.info("SystemVersion is: $systemVersion")
        return releaseManagerService.retrieveMicroServicesWithSystemVersion(systemVersion)
    }

    @PostMapping("/deploy")
    @ResponseStatus(HttpStatus.CREATED)
    fun deployService(@RequestBody microServiceDto: MicroServiceDto): Int {
        return releaseManagerService.updateServiceVersion(microServiceDto)
    }
}