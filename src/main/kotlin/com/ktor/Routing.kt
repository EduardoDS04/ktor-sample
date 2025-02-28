package com.ktor

import com.data.persistence.repository.PersistenceRestauranteRepository
import com.domain.models.Restaurante
import com.domain.models.UpdateRestaurante
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val restauranteRepository = PersistenceRestauranteRepository()

    routing {
        userRoutes()
        get("/") {
            call.respondText("Hello World!")
        }

        // Endpoints para Restaurantes
        route("/restaurantes") {
            // Obtener todos los restaurantes
            get {
                val restaurantes = restauranteRepository.getAllRestaurante()
                call.respond(restaurantes)
            }

            // Obtener restaurante por ID
            get("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest, "ID inválido")

                restauranteRepository.getRestauranteById(id)?.let { restaurante ->
                    call.respond(restaurante)
                } ?: call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
            }

            // Crear nuevo restaurante
            post {
                val restaurante = call.receive<Restaurante>()
                if (restauranteRepository.postRestaurante(restaurante)) {
                    call.respond(HttpStatusCode.Created, "Restaurante creado")
                } else {
                    call.respond(HttpStatusCode.Conflict, "El restaurante ya existe")
                }
            }

            // Actualizar restaurante
            put("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@put call.respond(HttpStatusCode.BadRequest, "ID inválido")

                val updateData = call.receive<UpdateRestaurante>()
                if (restauranteRepository.updateRestaurante(id, updateData)) {
                    call.respond(HttpStatusCode.OK, "Restaurante actualizado")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                }
            }
            // actualizar una parte de restaurante
            patch("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@patch call.respond(HttpStatusCode.BadRequest, "ID inválido")

                val updateData = call.receive<UpdateRestaurante>()
                if (restauranteRepository.updateRestaurante(id, updateData)) {
                    call.respond(HttpStatusCode.OK, "Restaurante actualizado parcialmente")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                }
            }

            // Eliminar restaurante
            delete("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest, "ID inválido")

                if (restauranteRepository.deleteRestaurante(id)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                }
            }
        }

        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
    }
}