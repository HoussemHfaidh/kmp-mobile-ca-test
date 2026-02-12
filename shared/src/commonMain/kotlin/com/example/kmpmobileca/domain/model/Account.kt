package com.example.kmpmobileca.domain.model

data class Account(
    val id: String,
    val label: String,
    val balance: Double,
    val operations: List<Operation>
)