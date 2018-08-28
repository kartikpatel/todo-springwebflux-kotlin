package com.fueledbysoda.todo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.core.env.Environment

@Scope("singleton")
@Configuration
class ApplicationProperties {
    @Value( "\${server.port}" )
    var port: Int? = null

    @Value( "\${server.appName}" )
    var appName: String? = null

    fun rootUrl(): String {
        return appName?.let { "https://$it.herokuapp.com" }  ?: "http://localhost:$port"
    }
}