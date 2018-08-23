package com.fueledbysoda.todo

fun port(): Int {
    return System.getenv("PORT")?.toInt() ?: 8080
}

fun rootUrl(): String {
    return System.getenv("HEROKU_APP_NAME")?.let { "https://$it.herokuapp.com" }
            ?: "http://localhost:${port()}"
}