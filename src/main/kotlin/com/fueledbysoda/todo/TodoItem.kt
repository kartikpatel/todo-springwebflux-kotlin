package com.fueledbysoda.todo

import java.util.*

data class TodoItem(
        val id: UUID = UUID.randomUUID(),
        var title: String,
        var completed: Boolean = false,
        val url: String = "${rootUrl()}/$id",
        var order: Int = -1)