package com.kotlinspring.entity

import com.kotlinspring.dto.ServiceDto
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "SystemVersion")
data class SystemVersion(
    @Id
    val id: Int?,
    val number: Int,

    val serviceDtos: String

)
