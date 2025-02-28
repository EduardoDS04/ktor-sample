package com.domain.usecase

import com.data.persistence.repository.PersistenceRestauranteRepository
import com.domain.models.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ProviderUseCase {
    // Logger para registrar las operaciones
    val logger: Logger = LoggerFactory.getLogger("RestauranteUseCaseLogger")

    // Repositorio de restaurantes
    private val repository = PersistenceRestauranteRepository()

    // Casos de uso relacionados con CRUD de restaurantes
    private val getAllRestaurantesUseCase = GetAllRestaurantesUseCase(repository)
    private val getRestauranteByIdUseCase = GetRestauranteByIdUseCase(repository)
    private val updateRestauranteUseCase = UpdateRestauranteUseCase(repository)
    private val insertRestauranteUseCase = InsertRestauranteUseCase(repository)
    private val deleteRestauranteUseCase = DeleteRestauranteUseCase(repository)

    // Métodos para acceder a los casos de uso

    // Obtener todos los restaurantes
    suspend fun getAllRestaurantes(): List<Restaurante> {
        return getAllRestaurantesUseCase()
    }

    // Obtener un restaurante por id
    suspend fun getRestauranteById(id: Int): Restaurante? {
        return getRestauranteByIdUseCase(id)
    }

    // Insertar un nuevo restaurante
    suspend fun insertRestaurante(restaurante: Restaurante): Boolean {
        return insertRestauranteUseCase(restaurante)
    }

    // Actualizar un restaurante existente
    suspend fun updateRestaurante(update: UpdateRestaurante, id: Int): Boolean {
        return updateRestauranteUseCase(update, id)  // Cambiar nombre por id
    }

    // Eliminar un restaurante por id
    suspend fun deleteRestaurante(id: Int): Boolean {
        return deleteRestauranteUseCase(id)
    }
}
