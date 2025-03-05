package com.ktor

import com.data.persistence.repository.UsuarioRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureAuth() {
    val appEnvironment = this.environment
    JwtConfig.init(appEnvironment)

    install(Authentication) {
        bearer("jwt") {
            realm = appEnvironment.config.property("ktor.jwt.realm").getString()
            authenticate { tokenCredential ->
                try {
                    // Verificar el token usando el verifier de JwtConfig
                    val decodedJWT = JwtConfig.getVerifier().verify(tokenCredential.token)

                    // Validar audiencia
                    if (!decodedJWT.audience.contains(JwtConfig.getAudience())) {
                        return@authenticate null
                    }

                    // Obtener userId del token
                    val userId = decodedJWT.getClaim("userId").asString()

                    // Buscar usuario en la base de datos
                    val usuario = UsuarioRepository.getUsuarioById(userId.toInt())

                    // Comparar token recibido con el almacenado
                    if (usuario != null && usuario.token == tokenCredential.token) {
                        UserIdPrincipal(userId) // Autenticación exitosa
                    } else {
                        null // Token no coincide o usuario no existe
                    }
                } catch (e: Exception) {
                    null // Cualquier error invalida la autenticación
                }
            }
        }
    }
}

