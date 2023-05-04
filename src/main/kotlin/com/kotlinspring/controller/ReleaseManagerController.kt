package com.kotlinspring.controller

import com.kotlinspring.dto.ServiceDto
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
    fun getServices(@RequestParam systemVersion: Int): ResponseEntity<List<ServiceDto>> {
        logger.info("SystemVersion is: $systemVersion")
        return ResponseEntity(releaseManagerService.retrieveSystemVersionServices(systemVersion), HttpStatus.OK)
    }

    @PostMapping("/deploy")
    @ResponseStatus(HttpStatus.CREATED)
    fun deployService(@RequestBody serviceDto: ServiceDto): Int {
        return releaseManagerService.updateServiceVersion(serviceDto)
    }
}