package com.example.kmpmobileca.ui.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs
import kotlin.math.round

fun formatEuro(amount: Double): String {

    val sign = if (amount < 0) "-" else ""
    val value = abs(amount)
    val scaled = round(value * 100) / 100

    val parts = scaled.toString().split(".")

    val integerPart = parts[0]
    val decimalPart = parts.getOrElse(1) { "00" }
        .padEnd(2, '0')

    val groupedInteger = integerPart
        .reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()

    return "$sign$groupedInteger,$decimalPart €"
}

fun formatDate(epochSeconds: Long): String {
    val date = Instant
        .fromEpochSeconds(epochSeconds)
        .toLocalDateTime(TimeZone.UTC)

    val day = date.dayOfMonth.toString().padStart(2, '0')
    val month = date.monthNumber.toString().padStart(2, '0')
    val year = date.year

    return "$day/$month/$year"
}



