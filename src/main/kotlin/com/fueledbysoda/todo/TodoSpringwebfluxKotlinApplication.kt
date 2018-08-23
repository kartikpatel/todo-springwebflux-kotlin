package com.fueledbysoda.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoSpringwebfluxKotlinApplication

fun main(args: Array<String>) {
    runApplication<TodoSpringwebfluxKotlinApplication>(*args)
}
