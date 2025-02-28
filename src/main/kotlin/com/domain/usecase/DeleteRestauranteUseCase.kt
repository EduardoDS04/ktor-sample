package com.domain.usecase

import com.domain.repository.RestauranteInterface

class DeleteRestauranteUseCase(private val repository: RestauranteInterface) {
    suspend operator fun invoke(id: Int): Boolean {
        return repository.deleteRestaurante(id)
    }
}
