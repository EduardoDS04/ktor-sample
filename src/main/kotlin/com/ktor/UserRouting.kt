package com.ktor

import com.data.persistence.repository.UsuarioRepository
import com.domain.models.Usuario
import com.domain.models.UsuarioLoginDTO
import com.domain.usecase.LoginUseCase
import com.domain.usecase.RegisterUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes() {
    route("/usuarios") {
        // Login
        post("/login") {
            val credentials = call.receive<UsuarioLoginDTO>()
            val success = LoginUseCase(UsuarioRepository).invoke(
                credentials.email,
                credentials.password
            )

            if (success) {
                call.respond(mapOf("message" to "Login exitoso"))
            } else {
                call.respond(
                    HttpStatusCode.Unauthorized,
                    mapOf("error" to "Credenciales incorrectas")
                )
            }
        }

        // Registro
        post("/register") {
            val usuario = call.receive<Usuario>()
            val result = RegisterUseCase(UsuarioRepository).invoke(usuario)

            if (result) {
                call.respond(
                    HttpStatusCode.Created,
                    mapOf("message" to "Usuario registrado exitosamente")
                )
            } else {
                call.respond(
                    HttpStatusCode.Conflict,
                    mapOf("error" to "El usuario ya existe (email o DNI duplicado)")
                )
            }
        }

        // Buscar por DNI
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