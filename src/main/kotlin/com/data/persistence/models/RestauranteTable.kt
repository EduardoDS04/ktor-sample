package com.data.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object RestauranteTable : IntIdTable("Restaurante") {
    val nombre = varchar("nombre", 100).uniqueIndex()
    val comida = varchar("comida", 255)
    val tiempoEntrega = varchar("tiempoEntrega", 10)
    val cantidad = integer("cantidad")
    val precio = decimal("precio", 10, 2)
    val imagen = varchar("imagen", 255).nullable()
}