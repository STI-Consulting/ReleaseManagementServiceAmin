package com.kotlinspring.entity

import jakarta.persistence.*

@Entity
@Table(name = "SYSTEMVERSIONS")
data class SystemVersion(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val number: Int,
    @OneToMany(
        mappedBy = "systemVersion",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val services: List<Service> = mutableListOf()

)
