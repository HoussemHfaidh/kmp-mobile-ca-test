package com.example.kmpmobileca.domain.model

data class Bank(
    val name: String,
    val isCreditAgricole: Boolean,
    val accounts: List<Account>
)