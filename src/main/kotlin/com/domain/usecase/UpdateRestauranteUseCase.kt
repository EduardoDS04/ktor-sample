package com.domain.usecase

import com.domain.models.UpdateRestaurante
import com.domain.repository.RestauranteInterface

class UpdateRestauranteUseCase(private val repository: RestauranteInterface) {
    suspend operator fun invoke(update: UpdateRestaurante, id: Int): Boolean {
        return repository.updateRestaurante(id, update)
    }
}
