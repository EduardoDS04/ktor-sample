package com.data.persistence.repository

import com.data.persistence.models.RestauranteDao
import com.data.persistence.models.RestauranteTable
import com.data.persistence.models.suspendTransaction
import com.domain.models.Restaurante
import com.domain.models.UpdateRestaurante
import com.domain.repository.RestauranteInterface


class PersistenceRestauranteRepository : RestauranteInterface {

    override suspend fun getAllRestaurante(): List<Restaurante> = suspendTransaction {
        RestauranteDao.all().map { it.toDomain() }
    }

    override suspend fun getRestauranteById(id: Int): Restaurante? = suspendTransaction {
        RestauranteDao.findById(id)?.toDomain()
    }

    override suspend fun postRestaurante(restaurante: Restaurante): Boolean = suspendTransaction {
        if (RestauranteDao.find { RestauranteTable.nombre eq restaurante.nombre }.empty()) {
            RestauranteDao.new {
                nombre = restaurante.nombre
                comida = restaurante.comida
                tiempoEntrega = restaurante.tiempoEntrega
                cantidad = restaurante.cantidad
                precio = restaurante.precio.toBigDecimal()
                imagen = restaurante.imagen
            }
            true
        } else {
            false
        }
    }

    override suspend fun updateRestaurante(id: Int, update: UpdateRestaurante): Boolean = suspendTransaction {
        RestauranteDao.findById(id)?.apply {
            update.nombre?.let { nombre = it }
            update.comida?.let { comida = it }
            update.tiempoEntrega?.let { tiempoEntrega = it }
            update.cantidad?.let { cantidad = it }
            update.precio?.let { precio = it.toBigDecimal() }
            update.imagen?.let { imagen = it }
        } != null
    }

    override suspend fun deleteRestaurante(id: Int): Boolean = suspendTransaction {
        RestauranteDao.findById(id)?.delete() != null
    }
}