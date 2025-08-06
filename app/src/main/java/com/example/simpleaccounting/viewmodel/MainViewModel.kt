package com.example.simpleaccounting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simpleaccounting.data.Transaction
import com.example.simpleaccounting.data.TransactionType
import com.example.simpleaccounting.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

class MainViewModel(private val repository: TransactionRepository) : ViewModel() {
    
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()
    
    private val _balance = MutableStateFlow(0.0)
    val balance: StateFlow<Double> = _balance.asStateFlow()
    
    private val _totalIncome = MutableStateFlow(0.0)
    val totalIncome: StateFlow<Double> = _totalIncome.asStateFlow()
    
    private val _totalExpense = MutableStateFlow(0.0)
    val totalExpense: StateFlow<Double> = _totalExpense.asStateFlow()
    
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            repository.getAllTransactions().collect { transactionList ->
                _transactions.value = transactionList
            }
        }
        
        viewModelScope.launch {
            repository.getAllCategories().collect { categoryList ->
                _categories.value = categoryList
            }
        }
        
        viewModelScope.launch {
            _balance.value = repository.getBalance()
            _totalIncome.value = repository.getTotalByType(TransactionType.INCOME)
            _totalExpense.value = repository.getTotalByType(TransactionType.EXPENSE)
        }
    }
    
    fun addTransaction(
        amount: Double,
        description: String,
        category: String,
        type: TransactionType
    ) {
        viewModelScope.launch {
            val transaction = Transaction(
                amount = amount,
                description = description,
                category = category,
                type = type,
                date = Date()
            )
            repository.insertTransaction(transaction)
            refreshData()
        }
    }
    
    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            repository.deleteTransaction(transaction)
            refreshData()
        }
    }
    
    private fun refreshData() {
        viewModelScope.launch {
            _balance.value = repository.getBalance()
            _totalIncome.value = repository.getTotalByType(TransactionType.INCOME)
            _totalExpense.value = repository.getTotalByType(TransactionType.EXPENSE)
        }
    }
}

class MainViewModelFactory(private val repository: TransactionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}