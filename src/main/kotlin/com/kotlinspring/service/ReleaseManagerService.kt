package com.kotlinspring.service

import com.kotlinspring.dto.MicroServiceDto
import com.kotlinspring.dto.SystemVersionDto
import com.kotlinspring.repository.MicroServiceRepository
import com.kotlinspring.repository.SystemVersionRepository
import org.springframework.stereotype.Service

@Service
class ReleaseManagerService(val microServiceRepository: MicroServiceRepository,
                            val systemVersionRepository: SystemVersionRepository) {

    private var currentSystemVersionDto = SystemVersionDto(0, 0, emptyList())


    fun retrieveMicroServicesWithSystemVersion(systemVersion: Int): List<com.kotlinspring.dto.MicroServiceDto> {
        val microservices = microServiceRepository.findAll().filter{ it.version <= systemVersion }
       return microservices.map { MicroServiceDto(it.id,it.name,it.version) }
    }

    fun updateServiceVersion(microServiceDto: com.kotlinspring.dto.MicroServiceDto): Int {
        val existingService = currentSystemVersionDto.microServiceDtos.find { it.name == microServiceDto.name }
        val newServices = if (existingService == null) {
            currentSystemVersionDto.microServiceDtos + microServiceDto
        } else {
            currentSystemVersionDto.microServiceDtos.map { if (it.name == microServiceDto.name) microServiceDto else it }
        }
        val newSystemVersionDto = if (existingService == null || existingService.version != microServiceDto.version) {
            SystemVersionDto(0, currentSystemVersionDto.number + 1, newServices)
        } else {
            currentSystemVersionDto
        }
        currentSystemVersionDto = newSystemVersionDto

        return currentSystemVersionDto.number
    }
}