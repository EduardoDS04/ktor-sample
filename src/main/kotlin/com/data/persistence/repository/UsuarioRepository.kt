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

            UsuarioDao.new {
                dni = usuario.dni
                nombre = usuario.nombre
                email = usuario.email
                password = usuario.password
                token = ""
            }.toDomain()
        }
    }
}