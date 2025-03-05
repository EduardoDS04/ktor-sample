package com.ktor

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*


class AuthorizationException(message: String) : RuntimeException(message)
class AuthenticationException(message: String) : RuntimeException(message)

fun Application.configureExceptionHandling() {
    install(StatusPages) {
        exception<AuthorizationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized, mapOf("error" to cause.message))
        }

        exception<AuthenticationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized, mapOf("error" to cause.message))
        }

        exception<Exception> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                mapOf("error" to "Error interno: ${cause.message}")
            )
        }
    }
}