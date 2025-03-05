
package com.ktor

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import com.domain.models.Usuario
import io.ktor.server.application.*
import java.util.*

object JwtConfig {
    private lateinit var secret: String
    private lateinit var issuer: String
    private lateinit var audience: String
    private var expiration: Long = 86400

    // Inicializa las propiedades a partir de la configuración
    fun init(environment: ApplicationEnvironment) {
        secret = environment.config.property("ktor.jwt.secret").getString()
        issuer = environment.config.property("ktor.jwt.issuer").getString()
        audience = environment.config.property("ktor.jwt.audience").getString()
        expiration = environment.config.property("ktor.jwt.expiration").getString().toLong()
    }

    // Generar el token
    fun generateToken(usuario: Usuario): String {
        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim("userId", usuario.id.toString())
            .withExpiresAt(Date(System.currentTimeMillis() + expiration * 1000)) // Expiración
            .sign(Algorithm.HMAC256(secret))
    }

    fun getVerifier(): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(secret))
            .withIssuer(issuer)
            .withAudience(audience)
            .build()
    }

    fun getAudience(): String = audience
}
