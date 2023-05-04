package com.kotlinspring.controller

import com.kotlinspring.service.ReleaseManagerService
import mu.KLogging
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/releaseManagement")
class ReleaseManagerController(val releaseManagerService: ReleaseManagerService) {

    companion object : KLogging()



}