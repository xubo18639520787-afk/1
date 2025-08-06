package com.example.simpleaccounting.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import java.util.Date

class InMemoryDataStore {
    private val _transactions = MutableStateFlow<List<Transaction>>(
        // 添加一些示例数据
        listOf(
            Transaction(1, 1000.0, "工资", "收入", TransactionType.INCOME, Date()),
            Transaction(2, 50.0, "午餐", "餐饮", TransactionType.EXPENSE, Date()),
            Transaction(3, 200.0, "购物", "购物", TransactionType.EXPENSE, Date()),
            Transaction(4, 30.0, "交通", "交通", TransactionType.EXPENSE, Date())
        )
    )
    
    private var nextId = 5L
    
    fun getAllTransactions(): Flow<List<Transaction>> {
        return _transactions.asStateFlow()
    }
    
    fun getTransactionsByType(type: TransactionType): Flow<List<Transaction>> {
        return _transactions.map { transactions ->
            transactions.filter { it.type == type }
        }
    }
    
    fun getTransactionsByCategory(category: String): Flow<List<Transaction>> {
        return _transactions.map { transactions ->
            transactions.filter { it.category == category }
        }
    }
    
    suspend fun getTotalByType(type: TransactionType): Double {
        return _transactions.value
            .filter { it.type == type }
            .sumOf { it.amount }
    }
    
    fun getAllCategories(): Flow<List<String>> {
        return _transactions.map { transactions ->
            transactions.map { it.category }.distinct().sorted()
        }
    }
    
    suspend fun insertTransaction(transaction: Transaction) {
        val newTransaction = transaction.copy(id = nextId++)
        val currentList = _transactions.value.toMutableList()
        currentList.add(newTransaction)
        _transactions.value = currentList.sortedByDescending { it.date }
    }
    
    suspend fun updateTransaction(transaction: Transaction) {
        val currentList = _transactions.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == transaction.id }
        if (index != -1) {
            currentList[index] = transaction
            _transactions.value = currentList.sortedByDescending { it.date }
        }
    }
    
    suspend fun deleteTransaction(transaction: Transaction) {
        val currentList = _transactions.value.toMutableList()
        currentList.removeAll { it.id == transaction.id }
        _transactions.value = currentList
    }
    
    suspend fun deleteTransactionById(id: Long) {
        val currentList = _transactions.value.toMutableList()
        currentList.removeAll { it.id == id }
        _transactions.value = currentList
    }
    
    companion object {
        @Volatile
        private var INSTANCE: InMemoryDataStore? = null
        
        fun getInstance(): InMemoryDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = InMemoryDataStore()
                INSTANCE = instance
                instance
            }
        }
    }
}