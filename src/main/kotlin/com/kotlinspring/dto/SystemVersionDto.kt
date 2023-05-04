package com.kotlinspring.dto

data class SystemVersionDto(
    val id: Int?,
    val number: Int,
    val serviceDtos: List<ServiceDto>
)
