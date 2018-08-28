package com.fueledbysoda.todo.controller

import com.fueledbysoda.todo.model.TodoItem
import com.fueledbysoda.todo.model.TodoRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import reactor.core.publisher.Mono
import java.util.*
import reactor.core.publisher.Flux
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.toMono
import java.io.FileNotFoundException


@RestController
@RequestMapping("/")
class TodoController(val todoRepository: TodoRepository = TodoRepository()) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    private fun getAllTodo(): Flux<TodoItem> = todoRepository.findAll()

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    private fun getTodoById(@PathVariable id: String): Mono<TodoItem> = todoRepository.findById(UUID.fromString(id))

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    private fun add(@RequestBody value: TodoItem): Mono<TodoItem> = todoRepository.save(value).toMono()

    @DeleteMapping
    private fun deleteAll(): Mono<Void> = todoRepository.deleteAll()

    @PatchMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    private fun update(@PathVariable id: String, @RequestBody value: Map<String, Any>) {
        todoRepository.update(UUID.fromString(id), value)
    }

    @DeleteMapping("/{id}")
    private fun deleteById(@PathVariable id: String): Mono<Void> = todoRepository.deleteById(UUID.fromString(id))

    @ExceptionHandler(FileNotFoundException::class)
    fun handleFileNotFoundException(): ResponseEntity<Void> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON_UTF8
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(): ResponseEntity<Void> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON_UTF8
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}