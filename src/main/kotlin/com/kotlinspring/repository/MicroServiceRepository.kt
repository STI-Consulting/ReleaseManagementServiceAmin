package com.kotlinspring.repository

import com.kotlinspring.entity.MicroService
import org.springframework.data.repository.CrudRepository

interface MicroServiceRepository : CrudRepository<MicroService, Int> {

}