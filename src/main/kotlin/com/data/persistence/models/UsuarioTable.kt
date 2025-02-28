package com.data.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object UsuarioTable : IntIdTable("Usuario") {
    val dni = varchar("dni", 20).uniqueIndex()
    val nombre = varchar("nombre", 100)
    val email = varchar("email", 255).uniqueIndex()
    val password = varchar("password", 255)
    val token = varchar("token", 255).default("")
}
