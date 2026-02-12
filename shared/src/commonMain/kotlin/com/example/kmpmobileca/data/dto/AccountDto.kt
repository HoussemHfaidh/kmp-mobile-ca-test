package com.example.kmpmobileca.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val id: String? = null,
    val label: String? = null,
    val balance: Double? = null,
    val operations: List<OperationDto>? = null,
)