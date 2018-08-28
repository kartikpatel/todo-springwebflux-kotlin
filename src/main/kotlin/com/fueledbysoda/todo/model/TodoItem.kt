package com.fueledbysoda.todo.model

import java.util.*

data class TodoItem(
        val id: UUID = UUID.randomUUID(),
        var title: String = "",
        var completed: Boolean = false,
        val url: String = "",
        var order: Int = -1)