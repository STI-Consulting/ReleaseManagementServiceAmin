package com.kotlinspring.service

import com.kotlinspring.dto.MicroServiceDto
import com.kotlinspring.dto.SystemVersionDto
import com.kotlinspring.entity.MicroService
import com.kotlinspring.entity.SystemVersion
import com.kotlinspring.repository.MicroServiceRepository
import com.kotlinspring.repository.SystemVersionRepository
import org.springframework.stereotype.Service

@Service
class ReleaseManagerService(
    val microServiceRepository: MicroServiceRepository,
    val systemVersionRepository: SystemVersionRepository
) {


    fun retrieveMicroServicesWithSystemVersion(systemVersion: Int): List<com.kotlinspring.dto.MicroServiceDto> {
        val microservices = microServiceRepository.findAll().filter { it.systemVersion?.equals(systemVersion) ?: true }
        return microservices.map { MicroServiceDto(it.id, it.name, it.version) }
    }

    fun updateServiceVersion(microServiceDto: com.kotlinspring.dto.MicroServiceDto): Int {
        val existingService = microServiceRepository.findAll().filter { it.name == microServiceDto.name }
        if (existingService == null) {
            increaseSystemVersion(microServiceDto)

        } else {

            val sameVersion = existingService.filter { it.version == microServiceDto.version }
            if (sameVersion == null) {
                increaseSystemVersion(microServiceDto)
            }
        }
        val allSystemVersion = systemVersionRepository.findAll()
        val lastSystemVersion = allSystemVersion.maxByOrNull { it.number }
        if (lastSystemVersion != null) {
            return lastSystemVersion.number
        }
        return 0
    }

    private fun increaseSystemVersion(microServiceDto: MicroServiceDto) {
        val entity = MicroService(null, microServiceDto.name, microServiceDto.version)
        microServiceRepository.save(entity)
        val allSystemVersion = systemVersionRepository.findAll()
        val currentSystemVersion = allSystemVersion.maxByOrNull { it.number }
        val newSystemNr = (currentSystemVersion?.number)?.plus(1)
        val systemEntity = newSystemNr?.let { SystemVersion(null, newSystemNr) }

        systemVersionRepository.save(systemEntity)
    }
}
