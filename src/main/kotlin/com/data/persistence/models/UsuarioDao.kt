package com.data.persistence.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import com.domain.models.Usuario

class UsuarioDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UsuarioDao>(UsuarioTable)

    var dni by UsuarioTable.dni    // Campo DNI añadido
    var nombre by UsuarioTable.nombre
    var email by UsuarioTable.email
    var password by UsuarioTable.password
    var token by UsuarioTable.token

    fun toDomain() = Usuario(
        id = id.value,
        dni = dni,
        nombre = nombre,
        email = email,
        password = password,
        token = token
    )
}
