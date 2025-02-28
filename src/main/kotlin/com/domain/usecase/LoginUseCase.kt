package com.domain.usecase


import com.domain.repository.UsuarioInterface
import com.data.security.PasswordHash

class LoginUseCase(private val usuarioRepository: UsuarioInterface) {

    suspend operator fun invoke(emailOrDni: String, password: String): Boolean {
        // Primero, verificar si el valor proporcionado es un email o un DNI
        val usuario = usuarioRepository.getUsuarioByEmail(emailOrDni)
            ?: usuarioRepository.getUsuarioByDni(emailOrDni)

        if (usuario == null) {
            return false  // El usuario con ese email o DNI no existe
        }

        // Verificar que la contraseña proporcionada coincida con el hash almacenado
        return PasswordHash.verify(password, usuario.password)
    }
}
