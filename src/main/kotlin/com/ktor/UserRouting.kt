package com.ktor

import com.data.persistence.repository.UsuarioRepository
import com.domain.models.Usuario
import com.domain.models.UsuarioLoginDTO
import com.domain.usecase.LoginUseCase
import com.domain.usecase.RegisterUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes() {
    route("/usuarios") {
        // Login
        post("/login") {
            val credentials = call.receive<UsuarioLoginDTO>()
            val token = LoginUseCase(
                usuarioRepository = UsuarioRepository
            ).invoke(
                credentials.email,
                credentials.password
            )

            token?.let {
                call.respond(mapOf("token" to it))
            } ?: call.respond(HttpStatusCode.Unauthorized, "Credenciales inválidas")
        }

        // Registro
        post("/register") {
            val usuario = call.receive<Usuario>()
            if (RegisterUseCase(UsuarioRepository).invoke(usuario)) {
                call.respond(HttpStatusCode.Created)
            } else {
                call.respond(HttpStatusCode.Conflict)
            }
        }

        // Endpoint protegido
        authenticate("jwt") {
            get("/{dni}") {
                val dni = call.parameters["dni"] ?: throw IllegalArgumentException("DNI requerido")
                val usuario = UsuarioRepository.getUsuarioByDni(dni)

                usuario?.let {
                    call.respond(it)
                } ?: call.respond(
                    HttpStatusCode.NotFound,
                    mapOf("error" to "Usuario no encontrado")
                )
            }
        }
    }
}