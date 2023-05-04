package com.kotlinspring.entity

import jakarta.persistence.*

@Entity
@Table(name = "Services")
data class Service(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val name: String,
    val version: Int
)
