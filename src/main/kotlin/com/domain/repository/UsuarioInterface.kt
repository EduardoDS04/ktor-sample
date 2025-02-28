package com.domain.repository

import com.domain.models.Usuario

interface UsuarioInterface {
    suspend fun getUsuarioByEmail(email: String): Usuario?
    suspend fun getUsuarioByDni(dni: String): Usuario?
    suspend fun createUsuario(usuario: Usuario): Usuario?
}