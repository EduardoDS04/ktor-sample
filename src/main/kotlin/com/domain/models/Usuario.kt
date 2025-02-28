package com.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    val id: Int? = null,
    val dni: String,
    val nombre: String,
    val email: String,
    val password: String,
    val token: String = ""
)
