package com.kotlinspring.service

import com.kotlinspring.dto.SystemVersionDto
import org.springframework.stereotype.Service

@Service
class ReleaseManagerService {
    private var currentSystemVersionDto = SystemVersionDto(0, 0,emptyList())


    fun retrieveSystemVersionServices(systemVersion: Int): List<com.kotlinspring.dto.ServiceDto> {
        return currentSystemVersionDto.serviceDtos.filter { it.version <= systemVersion }
    }

    fun updateServiceVersion(serviceDto: com.kotlinspring.dto.ServiceDto): Int {
        val existingService = currentSystemVersionDto.serviceDtos.find { it.name == serviceDto.name }
        val newServices = if (existingService == null) {
            currentSystemVersionDto.serviceDtos + serviceDto
        } else {
            currentSystemVersionDto.serviceDtos.map { if (it.name == serviceDto.name) serviceDto else it }
        }
        val newSystemVersionDto = if (existingService == null || existingService.version != serviceDto.version) {
            SystemVersionDto(0,currentSystemVersionDto.number + 1, newServices)
        } else {
            currentSystemVersionDto
        }
        currentSystemVersionDto = newSystemVersionDto

        return currentSystemVersionDto.number
    }
}