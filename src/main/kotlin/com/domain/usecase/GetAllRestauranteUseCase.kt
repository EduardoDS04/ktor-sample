package com.domain.usecase

import com.domain.models.Restaurante
import com.domain.repository.RestauranteInterface

class GetAllRestaurantesUseCase(private val repository: RestauranteInterface) {
    suspend operator fun invoke(): List<Restaurante> = repository.getAllRestaurante()
}
