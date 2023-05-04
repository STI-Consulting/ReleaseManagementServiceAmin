package com.kotlinspring.repository

import com.kotlinspring.entity.SystemVersion
import org.springframework.data.repository.CrudRepository

interface SystemVersionRepository: CrudRepository<SystemVersion, Int> {
}