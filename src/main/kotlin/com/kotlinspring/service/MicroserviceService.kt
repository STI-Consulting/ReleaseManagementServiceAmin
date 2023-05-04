package com.kotlinspring.service

import com.kotlinspring.repository.MicroServiceRepository
import org.springframework.stereotype.Service

@Service
class MicroserviceService(val microServiceRepository: MicroServiceRepository) {
}