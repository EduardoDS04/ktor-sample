package com.domain.repository

import com.domain.models.Restaurante
import com.domain.models.UpdateRestaurante

interface RestauranteInterface {

    suspend fun getAllRestaurante(): List<Restaurante>

    suspend fun getRestauranteById(id: Int): Restaurante?

    suspend fun postRestaurante(restaurante: Restaurante): Boolean

    suspend fun updateRestaurante(id: Int, update: UpdateRestaurante): Boolean

    suspend fun deleteRestaurante(id: Int): Boolean
}