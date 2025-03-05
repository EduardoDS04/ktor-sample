package com.domain.repository

import com.domain.models.Usuario

interface UsuarioInterface {
    suspend fun getUsuarioByEmail(email: String): Usuario?
    suspend fun getUsuarioByDni(dni: String): Usuario?
    suspend fun createUsuario(usuario: Usuario): Usuario?
    suspend fun updateToken(email: String, token: String): Boolean
    suspend fun getUsuarioByToken(token: String): Usuario?
    suspend fun getUsuarioById(id: Int): Usuario?
}