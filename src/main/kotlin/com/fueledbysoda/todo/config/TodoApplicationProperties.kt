package com.fueledbysoda.todo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("server")
class TodoApplicationProperties {
    lateinit var port: String

    lateinit var appName: String

    fun rootUrl(): String {
        return if (appName.isNotEmpty()) "https://$appName.herokuapp.com" else "http://localhost:$port"
    }
}