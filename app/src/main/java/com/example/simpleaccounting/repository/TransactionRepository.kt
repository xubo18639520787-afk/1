package com.example.simpleaccounting.repository

import com.example.simpleaccounting.data.InMemoryDataStore
import com.example.simpleaccounting.data.Transaction
import com.example.simpleaccounting.data.TransactionType
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val dataStore: InMemoryDataStore) {
    
    fun getAllTransactions(): Flow<List<Transaction>> {
        return dataStore.getAllTransactions()
    }
    
    fun getTransactionsByType(type: TransactionType): Flow<List<Transaction>> {
        return dataStore.getTransactionsByType(type)
    }
    
    fun getTransactionsByCategory(category: String): Flow<List<Transaction>> {
        return dataStore.getTransactionsByCategory(category)
    }
    
    suspend fun getTotalByType(type: TransactionType): Double {
        return dataStore.getTotalByType(type)
    }
    
    fun getAllCategories(): Flow<List<String>> {
        return dataStore.getAllCategories()
    }
    
    suspend fun insertTransaction(transaction: Transaction) {
        dataStore.insertTransaction(transaction)
    }
    
    suspend fun updateTransaction(transaction: Transaction) {
        dataStore.updateTransaction(transaction)
    }
    
    suspend fun deleteTransaction(transaction: Transaction) {
        dataStore.deleteTransaction(transaction)
    }
    
    suspend fun deleteTransactionById(id: Long) {
        dataStore.deleteTransactionById(id)
    }
    
    suspend fun getBalance(): Double {
        val totalIncome = getTotalByType(TransactionType.INCOME)
        val totalExpense = getTotalByType(TransactionType.EXPENSE)
        return totalIncome - totalExpense
    }
}
