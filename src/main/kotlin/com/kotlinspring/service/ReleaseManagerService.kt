package com.kotlinspring.service

import com.kotlinspring.dto.SystemVersion
import org.springframework.stereotype.Service

@Service
class ReleaseManagerService {
    private var currentSystemVersion = SystemVersion(0, emptyList())


    fun retrieveSystemVersionServices(systemVersion: Int): List<com.kotlinspring.dto.Service> {
        return currentSystemVersion.services.filter { it.version <= systemVersion }
    }
}