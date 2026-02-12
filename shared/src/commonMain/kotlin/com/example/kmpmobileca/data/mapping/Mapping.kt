package com.example.kmpmobileca.data.mapping

import com.example.kmpmobileca.data.dto.AccountDto
import com.example.kmpmobileca.data.dto.BankDto
import com.example.kmpmobileca.data.dto.OperationDto
import com.example.kmpmobileca.domain.model.Account
import com.example.kmpmobileca.domain.model.Bank
import com.example.kmpmobileca.domain.model.Operation

/**
 *  I don't have the API interface contract,
 *  that's why I made the fields nullable.
 */

fun BankDto.toBankDomain() = Bank(
    name = name?.trim().orEmpty(),
    isCreditAgricole = (isCA == 1),
    accounts = accounts?.map { accountDto ->
        accountDto.toAccountDomain()
    } ?: emptyList()
)

fun AccountDto.toAccountDomain() = Account(
    id = id?.trim().orEmpty(),
    label = label?.trim().orEmpty(),
    balance = balance ?: 0.0,
    operations = operations?.map { operation ->
        operation.toOperationDomain()
    }?: emptyList()
)

fun OperationDto.toOperationDomain() = Operation(
    id = id?.trim().orEmpty(),
    title = title?.trim().orEmpty(),
    amount = amount?.trim()?.toAmountDouble() ?: 0.0,
    category = category?.trim().orEmpty(),
    date = date?.toDateSeconds() ?: 0L
)


/**
 *  Convert Amount to Double
 *
 * In my situation, I would return the default value
 *   if there were an exception.
 */
private fun String.toAmountDouble(default: Double = 0.0): Double =
    trim()
        .replace(" ", "")
        .replace(",", ".")
        .toDoubleOrNull() ?: default

/**
 *  Convert String Date to Long
 *  In my situation, I would return the default value
 *  if there were an exception.
 */

private fun String.toDateSeconds(default: Long = 0L): Long =
    trim().toLongOrNull() ?: default