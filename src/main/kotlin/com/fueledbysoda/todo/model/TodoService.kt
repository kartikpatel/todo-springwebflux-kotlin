package com.fueledbysoda.todo.model

import com.fueledbysoda.todo.config.TodoApplicationProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService {
    @Autowired
    lateinit var props: TodoApplicationProperties

    private val items = arrayListOf<TodoItem>()

    fun list(): List<TodoItem> {
        return items
    }

    fun clear(): List<TodoItem> {
        items.clear()
        return items
    }

    fun add(input: TodoItem): TodoItem {
        val id = UUID.randomUUID()
        val url = "${props.rootUrl()}/$id"
        val item = TodoItem(id = id, title = input.title, order = input.order, url = url)
        items.add(item)
        return item
    }

    fun get(id: UUID): TodoItem? {
        return items.find { it.id == id}
    }

    fun patch(id: UUID, finalState: Map<String, Any>): TodoItem? {
        return get(id)?.let { item ->
            finalState["title"]?.let { item.title = it.toString() }
            finalState["completed"]?.let { item.completed = it.toString().toBoolean() }
            finalState["order"]?.let { item.order = it.toString().toInt() }
            return item
        }
    }

    fun delete(id: UUID): UUID? {
        return get(id)?.let { item ->
            items.remove(item)
            return item.id
        }
    }
}
