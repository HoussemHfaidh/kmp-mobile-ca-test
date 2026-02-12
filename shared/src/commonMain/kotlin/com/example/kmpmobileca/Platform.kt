package com.example.kmpmobileca

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform