package com.data.persistence.repository

import com.domain.models.Usuario
import com.domain.repository.UsuarioInterface
import com.data.persistence.models.UsuarioDao
import com.data.persistence.models.UsuarioTable
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object UsuarioRepository : UsuarioInterface {

    override suspend fun getUsuarioByDni(dni: String): Usuario? {
        return newSuspendedTransaction {
            UsuarioDao.find { UsuarioTable.dni eq dni }
                .firstOrNull()
                ?.toDomain()
        }
    }

    override suspend fun getUsuarioByEmail(email: String): Usuario? {
        return newSuspendedTransaction {
            UsuarioDao.find { UsuarioTable.email eq email }
                .firstOrNull()
                ?.toDomain()
        }
    }

    override suspend fun createUsuario(usuario: Usuario): Usuario? {
        return newSuspendedTransaction {
            val existeUsuario = UsuarioDao.find {
                (UsuarioTable.email eq usuario.email) or (UsuarioTable.dni eq usuario.dni)
            }.any()

            if (existeUsuario) return@newSuspendedTransaction null

            // Crear el usuario en la base de datos con un token vacío
            UsuarioDao.new {
                dni = usuario.dni
                nombre = usuario.nombre
                email = usuario.email
                password = usuario.password
                token = "" // Token vacío por defecto
            }.toDomain() // Devuelve el usuario creado
        }
    }

    override suspend fun updateToken(email: String, token: String): Boolean {
        return newSuspendedTransaction {
            val usuario = UsuarioDao.find { UsuarioTable.email eq email }.firstOrNull()
            usuario?.apply { this.token = token } // Se actualiza el token
            usuario != null // Si el usuario existe, se actualiza el token
        }
    }


    override suspend fun getUsuarioByToken(token: String): Usuario? {
        return newSuspendedTransaction {
            UsuarioDao.find { UsuarioTable.token eq token }
                .firstOrNull()
                ?.toDomain()
        }
    }
    override suspend fun getUsuarioById(id: Int): Usuario? {
        return newSuspendedTransaction {
            UsuarioDao.findById(id)?.toDomain()
        }
    }
}