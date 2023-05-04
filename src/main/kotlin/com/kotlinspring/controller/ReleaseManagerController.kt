package com.kotlinspring.controller

import com.kotlinspring.dto.Service
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
    fun getServices(@RequestParam systemVersion: Int): ResponseEntity<List<Service>> {
        logger.info("SystemVersion is: $systemVersion")
        return ResponseEntity(releaseManagerService.retrieveSystemVersionServices(systemVersion), HttpStatus.OK)
    }

    @PostMapping("/deploy")
    fun deployService(@RequestBody service: Service): ResponseEntity<Int> {
        return ResponseEntity(releaseManagerService.updateServiceVersion(service), HttpStatus.OK)
    }
}