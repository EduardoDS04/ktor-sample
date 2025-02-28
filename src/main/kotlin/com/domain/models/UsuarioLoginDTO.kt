package com.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioLoginDTO(
    val email: String,
    val password: String
)
