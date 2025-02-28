package com.data.persistence.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import com.domain.models.Restaurante

class RestauranteDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RestauranteDao>(RestauranteTable)

    var nombre by RestauranteTable.nombre
    var comida by RestauranteTable.comida
    var tiempoEntrega by RestauranteTable.tiempoEntrega
    var cantidad by RestauranteTable.cantidad
    var precio by RestauranteTable.precio
    var imagen by RestauranteTable.imagen

    fun toDomain() = Restaurante(
        id = id.value,
        nombre = nombre,
        comida = comida,
        tiempoEntrega = tiempoEntrega,
        cantidad = cantidad,
        precio = precio.toDouble(),
        imagen = imagen
    )
}
