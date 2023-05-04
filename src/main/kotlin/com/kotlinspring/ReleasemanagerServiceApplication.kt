package com.kotlinspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReleasemanagerServiceApplication

fun main(args: Array<String>) {
    runApplication<ReleasemanagerServiceApplication>(*args)
}
