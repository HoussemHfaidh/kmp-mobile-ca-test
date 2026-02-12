package com.example.kmpmobileca.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BankDto(
    val name: String? = null,
    val isCA: Int? = null,
    val accounts: List<AccountDto>? = null
)