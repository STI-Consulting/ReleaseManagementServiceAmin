package com.kotlinspring.service

import com.kotlinspring.dto.SystemVersion
import org.springframework.stereotype.Service

@Service
class ReleaseManagerService {
    private var currentSystemVersion = SystemVersion(0, emptyList())


    fun retrieveSystemVersionServices(systemVersion: Int): List<com.kotlinspring.dto.Service> {
        return currentSystemVersion.services.filter { it.version <= systemVersion }
    }

    fun updateServiceVersion(service: com.kotlinspring.dto.Service): Int {
        val existingService = currentSystemVersion.services.find { it.name == service.name }
        val newServices = if (existingService == null) {
            currentSystemVersion.services + service
        } else {
            currentSystemVersion.services.map { if (it.name == service.name) service else it }
        }
        val newSystemVersion = if (existingService == null || existingService.version != service.version) {
            SystemVersion(currentSystemVersion.number + 1, newServices)
        } else {
            currentSystemVersion
        }
        currentSystemVersion = newSystemVersion

        return currentSystemVersion.number
    }
}