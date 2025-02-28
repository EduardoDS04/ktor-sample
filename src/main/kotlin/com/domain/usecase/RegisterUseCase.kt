package com.domain.usecase

import com.domain.models.Usuario
import com.domain.repository.UsuarioInterface
import com.data.security.PasswordHash


class RegisterUseCase(private val usuarioRepository: UsuarioInterface) {
    suspend operator fun invoke(usuario: Usuario): Boolean {
        if (usuarioRepository.getUsuarioByEmail(usuario.email) != null) return false
        if (usuarioRepository.getUsuarioByDni(usuario.dni) != null) return false

        val hashedPassword = PasswordHash.hash(usuario.password)
        val newUsuario = usuario.copy(password = hashedPassword)

        return usuarioRepository.createUsuario(newUsuario) != null
    }
}
