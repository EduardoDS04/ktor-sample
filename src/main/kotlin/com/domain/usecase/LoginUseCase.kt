package com.domain.usecase


import com.data.security.PasswordHash
import com.domain.repository.UsuarioInterface
import com.ktor.JwtConfig

class LoginUseCase(
    private val usuarioRepository: UsuarioInterface
) {
    suspend operator fun invoke(emailOrDni: String, password: String): String? {
        val usuario = usuarioRepository.getUsuarioByEmail(emailOrDni)
            ?: usuarioRepository.getUsuarioByDni(emailOrDni)

        return if (usuario != null && PasswordHash.verify(password, usuario.password)) {
            val token = JwtConfig.generateToken(usuario) // Generar el token
            usuarioRepository.updateToken(usuario.email, token) // Guardar el nuevo token en la base de datos
            token // Devolver el token al cliente
        } else {
            null
        }
    }
}
