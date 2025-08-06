package com.example.simpleaccounting.data

import java.util.Date

data class Transaction(
    val id: Long = 0,
    val amount: Double,
    val description: String,
    val category: String,
    val type: TransactionType,
    val date: Date = Date()
)

enum class TransactionType {
    INCOME,    // 收入
    EXPENSE    // 支出
}