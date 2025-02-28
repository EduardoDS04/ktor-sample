package com.domain.mapping

import com.data.persistence.models.RestauranteDao
import com.domain.models.Restaurante
import com.domain.models.UpdateRestaurante

fun RestauranteDaoToRestaurante(restauranteDao: RestauranteDao): Restaurante {
    return Restaurante(
        nombre = restauranteDao.nombre,
        comida = restauranteDao.comida,
        tiempoEntrega = restauranteDao.tiempoEntrega,
        cantidad = restauranteDao.cantidad,
        precio = restauranteDao.precio.toDouble(),
        imagen = restauranteDao.imagen ?: "default.jpg"
    )
}

fun UpdateRestauranteToRestaurante(update: UpdateRestaurante): Restaurante {
    return Restaurante(
        nombre = requireNotNull(update.nombre) { "Nombre es requerido" },
        comida = requireNotNull(update.comida) { "Comida es requerida" },
        tiempoEntrega = requireNotNull(update.tiempoEntrega) { "Tiempo de entrega es requerido" },
        cantidad = requireNotNull(update.cantidad) { "Cantidad es requerida" },
        precio = requireNotNull(update.precio) { "Precio es requerido" },
        imagen = update.imagen ?: "default.jpg"
    )
}