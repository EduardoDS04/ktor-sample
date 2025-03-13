package com.ktor

import com.data.persistence.repository.UsuarioRepository
import com.domain.models.Usuario
import com.domain.models.UsuarioLoginDTO
import com.domain.usecase.LoginUseCase
import com.domain.usecase.RegisterUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes() {
    // Obtenemos el logger de la aplicación
    val logger = application.log

    route("/usuarios") {
        // Login
        post("/login") {
            val requestHost = call.request.origin.remoteHost
            logger.info("Intento de login desde: $requestHost")

            val credentials = call.receive<UsuarioLoginDTO>()
            val token = LoginUseCase(
                usuarioRepository = UsuarioRepository
            ).invoke(
                credentials.email,
                credentials.password
            )

            token?.let {
                logger.info("Login exitoso para usuario: ${credentials.email}")
                call.respond(mapOf("token" to it))
            } ?: run {
                logger.info("Login fallido para usuario: ${credentials.email}")
                call.respond(HttpStatusCode.Unauthorized, "Credenciales inválidas")
            }
        }

        // Registro
        post("/register") {
            val usuario = call.receive<Usuario>()
            logger.info("Intento de registro para usuario: ${usuario.email}")

            if (RegisterUseCase(UsuarioRepository).invoke(usuario)) {
                logger.info("Registro exitoso para usuario: ${usuario.email}")
                call.respond(HttpStatusCode.Created)
            } else {
                logger.info("Registro fallido: ${usuario.email} - Usuario ya existe")
                call.respond(HttpStatusCode.Conflict)
            }
        }

        // Endpoint protegido
        authenticate("jwt") {
            get("/{dni}") {
                val dni = call.parameters["dni"] ?: throw IllegalArgumentException("DNI requerido")
                logger.info("Buscando usuario con DNI: $dni")

                val usuario = UsuarioRepository.getUsuarioByDni(dni)

                usuario?.let {
                    logger.info("Usuario encontrado con DNI: $dni")
                    call.respond(it)
                } ?: run {
                    logger.info("Usuario no encontrado con DNI: $dni")
                    call.respond(
                        HttpStatusCode.NotFound,
                        mapOf("error" to "Usuario no encontrado")
                    )
                }
            }
        }
    }
}