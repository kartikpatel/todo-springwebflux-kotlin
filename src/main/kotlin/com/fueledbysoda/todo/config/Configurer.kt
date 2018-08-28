package com.fueledbysoda.todo.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class Configurer: WebFluxConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        super.addCorsMappings(registry)

        registry.addMapping("*")
                .allowedMethods(HttpMethod.GET.name, HttpMethod.POST.name, HttpMethod.OPTIONS.name, HttpMethod.DELETE.name, HttpMethod.PATCH.name)
                .allowedHeaders("X-PINGARUNNER", "Content-Type")
    }
}