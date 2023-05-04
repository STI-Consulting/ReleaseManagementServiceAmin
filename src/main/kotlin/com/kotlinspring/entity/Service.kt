package com.kotlinspring.entity

import jakarta.persistence.*

@Entity
@Table(name = "Services")
data class Service(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val name: String,
    val version: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SYSTEMVERSIONS_ID", nullable = false)
    val systemVersion: SystemVersion? = null
)
