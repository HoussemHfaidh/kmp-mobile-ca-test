package com.example.kmpmobileca.domain.model

data class Operation(
    val id: String,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Long
)