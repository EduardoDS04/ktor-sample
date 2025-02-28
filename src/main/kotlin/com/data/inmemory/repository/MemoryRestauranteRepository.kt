package com.data.inmemory.repository

import com.data.inmemory.models.RestauranteData
import com.domain.models.Restaurante
import com.domain.models.UpdateRestaurante
import com.domain.repository.RestauranteInterface


class MemoryRestauranteRepository : RestauranteInterface {

    override suspend fun getAllRestaurante(): List<Restaurante> {
        return RestauranteData.listRestaurante
    }

    override suspend fun getRestauranteById(id: Int): Restaurante? {
        return RestauranteData.listRestaurante.find { it.id == id }
    }

    override suspend fun postRestaurante(restaurante: Restaurante): Boolean {
        return if (RestauranteData.listRestaurante.any { it.nombre == restaurante.nombre }) {
            false
        } else {
            val newId = RestauranteData.listRestaurante.maxOfOrNull { it.id ?: 0 }?.plus(1) ?: 1
            RestauranteData.listRestaurante.add(restaurante.copy(id = newId))
            true
        }
    }

    override suspend fun updateRestaurante(id: Int, update: UpdateRestaurante): Boolean {
        val index = RestauranteData.listRestaurante.indexOfFirst { it.id == id }
        return if (index != -1) {
            val original = RestauranteData.listRestaurante[index]
            RestauranteData.listRestaurante[index] = original.copy(
                nombre = update.nombre ?: original.nombre,
                comida = update.comida ?: original.comida,
                tiempoEntrega = update.tiempoEntrega ?: original.tiempoEntrega,
                cantidad = update.cantidad ?: original.cantidad,
                precio = update.precio ?: original.precio,
                imagen = update.imagen ?: original.imagen
            )
            true
        } else {
            false
        }
    }

    override suspend fun deleteRestaurante(id: Int): Boolean {
        return RestauranteData.listRestaurante.removeIf { it.id == id }
    }
}