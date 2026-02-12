package com.example.kmpmobileca.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class OperationDto(
    val id: String? = null,
    val title: String? = null,
    val amount: String? = null,
    val category: String? = null,
    val date: String? = null
)