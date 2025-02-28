package com.domain.usecase

import com.domain.repository.RestauranteInterface
import com.domain.models.Restaurante

class GetRestauranteByIdUseCase(private val repository: RestauranteInterface) {

    suspend operator fun invoke(id: Int): Restaurante? {
        // Buscar restaurante por id
        return repository.getRestauranteById(id)
    }
}
