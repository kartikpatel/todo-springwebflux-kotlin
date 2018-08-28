package com.fueledbysoda.todo.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.io.FileNotFoundException
import java.util.*

@Repository
class TodoRepository {
    @Autowired
    lateinit var todoService: TodoService

    fun save(entity: TodoItem): Mono<TodoItem> = todoService.add(entity).toMono()

    fun findAll(): Flux<TodoItem> = todoService.list().toFlux()

    fun deleteById(id: UUID): Mono<Void> {
        todoService.delete(id)
        return Mono.empty()
    }

    fun deleteAll(): Mono<Void> {
        todoService.clear()
        return Mono.empty()
    }

    fun findById(id: UUID): Mono<TodoItem> = todoService.get(id)?.toMono() ?: throw FileNotFoundException()

    fun update(id: UUID, map: Map<String, Any>): Mono<TodoItem> = todoService.patch(id, map)?.toMono() ?: throw FileNotFoundException()
}