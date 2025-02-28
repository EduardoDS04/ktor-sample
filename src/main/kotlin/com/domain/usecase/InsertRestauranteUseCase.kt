package com.domain.usecase

import com.domain.models.Restaurante
import com.domain.repository.RestauranteInterface

class InsertRestauranteUseCase(private val repository: RestauranteInterface) {
    suspend operator fun invoke(restaurante: Restaurante): Boolean {
        return repository.postRestaurante(restaurante)
    }
}
