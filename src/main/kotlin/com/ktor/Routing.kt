package com.ktor

import com.data.persistence.repository.PersistenceRestauranteRepository
import com.domain.models.Restaurante
import com.domain.models.UpdateRestaurante
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// Variable global para controlar si se muestran los logs de GET
private var showGetLogs = false

fun Application.configureRouting() {
    val restauranteRepository = PersistenceRestauranteRepository()
    val logger = log

    routing {
        userRoutes()

        authenticate("jwt") {
            route("/restaurantes") {
                // Obtener todos los restaurantes
                get {
                    // Solo registrar si showGetLogs es true
                    if (showGetLogs) {
                        logger.info("GET: Solicitando lista de todos los restaurantes")
                    }

                    val restaurantes = restauranteRepository.getAllRestaurante()

                    // Solo registrar si showGetLogs es true
                    if (showGetLogs) {
                        logger.info("GET: Retornando ${restaurantes.size} restaurantes")
                        // Resetear después de mostrar
                        showGetLogs = false
                    }

                    call.respond(restaurantes)
                }

                // Obtener restaurante por ID
                get("{id}") {
                    val id = call.parameters["id"]?.toIntOrNull()
                        ?: return@get call.respond(HttpStatusCode.BadRequest, "ID inválido")

                    // Siempre mostramos logs para GET específicos
                    logger.info("GET: Buscando restaurante con ID: $id")

                    restauranteRepository.getRestauranteById(id)?.let { restaurante ->
                        logger.info("GET: Restaurante encontrado con ID: $id")
                        call.respond(restaurante)
                    } ?: run {
                        logger.info("GET: Restaurante no encontrado con ID: $id")
                        call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                    }
                }

                // Crear nuevo restaurante
                post {
                    val restaurante = call.receive<Restaurante>()
                    logger.info("POST: Creando nuevo restaurante: ${restaurante.nombre}")

                    // Activar logs para el próximo GET
                    showGetLogs = true

                    if (restauranteRepository.postRestaurante(restaurante)) {
                        logger.info("POST: Restaurante creado exitosamente: ${restaurante.nombre}")
                        call.respond(HttpStatusCode.Created, "Restaurante creado")
                    } else {
                        logger.info("POST: Error al crear restaurante: ${restaurante.nombre} - Ya existe")
                        call.respond(HttpStatusCode.Conflict, "El restaurante ya existe")
                    }
                }

                // Actualizar restaurante
                put("{id}") {
                    val id = call.parameters["id"]?.toIntOrNull()
                        ?: return@put call.respond(HttpStatusCode.BadRequest, "ID inválido")

                    logger.info("PUT: Actualizando restaurante con ID: $id")

                    // Activar logs para el próximo GET
                    showGetLogs = true

                    val updateData = call.receive<UpdateRestaurante>()
                    if (restauranteRepository.updateRestaurante(id, updateData)) {
                        logger.info("PUT: Restaurante actualizado exitosamente con ID: $id")
                        call.respond(HttpStatusCode.OK, "Restaurante actualizado")
                    } else {
                        logger.info("PUT: Restaurante no encontrado con ID: $id")
                        call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                    }
                }

                // Actualizar una parte de restaurante
                patch("{id}") {
                    val id = call.parameters["id"]?.toIntOrNull()
                        ?: return@patch call.respond(HttpStatusCode.BadRequest, "ID inválido")

                    logger.info("PATCH: Actualizando parcialmente restaurante con ID: $id")

                    // Activar logs para el próximo GET
                    showGetLogs = true

                    val updateData = call.receive<UpdateRestaurante>()
                    if (restauranteRepository.updateRestaurante(id, updateData)) {
                        logger.info("PATCH: Restaurante actualizado parcialmente con ID: $id")
                        call.respond(HttpStatusCode.OK, "Restaurante actualizado parcialmente")
                    } else {
                        logger.info("PATCH: Restaurante no encontrado con ID: $id")
                        call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                    }
                }

                // Eliminar restaurante
                delete("{id}") {
                    val id = call.parameters["id"]?.toIntOrNull()
                        ?: return@delete call.respond(HttpStatusCode.BadRequest, "ID inválido")

                    logger.info("DELETE: Eliminando restaurante con ID: $id")

                    // Activar logs para el próximo GET
                    showGetLogs = true

                    if (restauranteRepository.deleteRestaurante(id)) {
                        logger.info("DELETE: Restaurante eliminado exitosamente con ID: $id")
                        call.respond(HttpStatusCode.NoContent)
                    } else {
                        logger.info("DELETE: Restaurante no encontrado con ID: $id")
                        call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                    }
                }
            }
        }

        staticResources("/static", "static")
    }
}