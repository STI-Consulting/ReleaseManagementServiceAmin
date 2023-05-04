package com.kotlinspring.repository

import com.kotlinspring.entity.Service
import org.springframework.data.repository.CrudRepository

interface ServiceRepository : CrudRepository<Service, Int> {

}