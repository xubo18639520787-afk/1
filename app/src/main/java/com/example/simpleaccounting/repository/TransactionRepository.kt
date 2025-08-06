package com.example.simpleaccounting.repository

import com.example.simpleaccounting.data.AppDatabase
import com.example.simpleaccounting.data.Transaction
import com.example.simpleaccounting.data.TransactionType
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val database: AppDatabase) {
    
    private val transactionDao = database.transactionDao()
    
    fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions()
    }
    
    fun getTransactionsByType(type: TransactionType): Flow<List<Transaction>> {
        return transactionDao.getTransactionsByType(type)
    }
    
    fun getTransactionsByCategory(category: String): Flow<List<Transaction>> {
        return transactionDao.getTransactionsByCategory(category)
    }
    
    suspend fun getTotalByType(type: TransactionType): Double {
        return transactionDao.getTotalByType(type) ?: 0.0
    }
    
    fun getAllCategories(): Flow<List<String>> {
        return transactionDao.getAllCategories()
    }
    
    suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }
    
    suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(transaction)
    }
    
    suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }
    
    suspend fun deleteTransactionById(id: Long) {
        transactionDao.deleteTransactionById(id)
    }
    
    suspend fun getBalance(): Double {
        val totalIncome = getTotalByType(TransactionType.INCOME)
        val totalExpense = getTotalByType(TransactionType.EXPENSE)
        return totalIncome - totalExpense
    }
}