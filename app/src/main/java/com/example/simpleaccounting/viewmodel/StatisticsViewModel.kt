package com.example.simpleaccounting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simpleaccounting.data.Transaction
import com.example.simpleaccounting.data.TransactionType
import com.example.simpleaccounting.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class StatisticsViewModel(private val repository: TransactionRepository) : ViewModel() {
    
    private val _selectedPeriod = MutableStateFlow(StatisticsPeriod.MONTH)
    val selectedPeriod: StateFlow<StatisticsPeriod> = _selectedPeriod.asStateFlow()
    
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    
    private val _categoryStatistics = MutableStateFlow<List<CategoryStatistic>>(emptyList())
    val categoryStatistics: StateFlow<List<CategoryStatistic>> = _categoryStatistics.asStateFlow()
    
    private val _monthlyStatistics = MutableStateFlow<List<MonthlyStatistic>>(emptyList())
    val monthlyStatistics: StateFlow<List<MonthlyStatistic>> = _monthlyStatistics.asStateFlow()
    
    private val _totalIncome = MutableStateFlow(0.0)
    val totalIncome: StateFlow<Double> = _totalIncome.asStateFlow()
    
    private val _totalExpense = MutableStateFlow(0.0)
    val totalExpense: StateFlow<Double> = _totalExpense.asStateFlow()
    
    private val _balance = MutableStateFlow(0.0)
    val balance: StateFlow<Double> = _balance.asStateFlow()
    
    init {
        loadData()
    }
    
    fun setPeriod(period: StatisticsPeriod) {
        _selectedPeriod.value = period
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            repository.getAllTransactions().collect { allTransactions ->
                val filteredTransactions = filterTransactionsByPeriod(allTransactions, _selectedPeriod.value)
                _transactions.value = filteredTransactions
                
                calculateTotals(filteredTransactions)
                calculateCategoryStatistics(filteredTransactions)
                calculateMonthlyStatistics(allTransactions)
            }
        }
    }
    
    private fun calculateTotals(transactions: List<Transaction>) {
        val income = transactions
            .filter { it.type == TransactionType.INCOME }
            .sumOf { it.amount }
        
        val expense = transactions
            .filter { it.type == TransactionType.EXPENSE }
            .sumOf { it.amount }
        
        _totalIncome.value = income
        _totalExpense.value = expense
        _balance.value = income - expense
    }
    
    private fun calculateCategoryStatistics(transactions: List<Transaction>) {
        val expenseTransactions = transactions.filter { it.type == TransactionType.EXPENSE }
        val totalExpense = expenseTransactions.sumOf { it.amount }
        
        if (totalExpense == 0.0) {
            _categoryStatistics.value = emptyList()
            return
        }
        
        val categoryMap = expenseTransactions
            .groupBy { it.category }
            .mapValues { (_, transactions) -> transactions.sumOf { it.amount } }
            .toList()
            .sortedByDescending { (_, amount) -> amount }
        
        _categoryStatistics.value = categoryMap.map { (category, amount) ->
            CategoryStatistic(
                category = category,
                amount = amount,
                percentage = amount / totalExpense
            )
        }
    }
    
    private fun calculateMonthlyStatistics(allTransactions: List<Transaction>) {
        val calendar = Calendar.getInstance()
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)
        
        // 获取过去6个月的数据
        val monthlyData = mutableListOf<MonthlyStatistic>()
        
        for (i in 5 downTo 0) {
            calendar.set(Calendar.YEAR, currentYear)
            calendar.set(Calendar.MONTH, currentMonth - i)
            
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            
            val monthTransactions = allTransactions.filter {
                val transactionCalendar = Calendar.getInstance().apply { time = it.date }
                transactionCalendar.get(Calendar.YEAR) == year && 
                transactionCalendar.get(Calendar.MONTH) == month
            }
            
            val income = monthTransactions
                .filter { it.type == TransactionType.INCOME }
                .sumOf { it.amount }
            
            val expense = monthTransactions
                .filter { it.type == TransactionType.EXPENSE }
                .sumOf { it.amount }
            
            val monthName = when (month) {
                Calendar.JANUARY -> "1月"
                Calendar.FEBRUARY -> "2月"
                Calendar.MARCH -> "3月"
                Calendar.APRIL -> "4月"
                Calendar.MAY -> "5月"
                Calendar.JUNE -> "6月"
                Calendar.JULY -> "7月"
                Calendar.AUGUST -> "8月"
                Calendar.SEPTEMBER -> "9月"
                Calendar.OCTOBER -> "10月"
                Calendar.NOVEMBER -> "11月"
                Calendar.DECEMBER -> "12月"
                else -> "$month月"
            }
            
            monthlyData.add(
                MonthlyStatistic(
                    month = monthName,
                    income = income,
                    expense = expense
                )
            )
        }
        
        _monthlyStatistics.value = monthlyData
    }
    
    private fun filterTransactionsByPeriod(
        transactions: List<Transaction>,
        period: StatisticsPeriod
    ): List<Transaction> {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = calendar.timeInMillis
        
        val periodStartMillis = when (period) {
            StatisticsPeriod.WEEK -> currentTimeMillis - TimeUnit.DAYS.toMillis(7)
            StatisticsPeriod.MONTH -> {
                calendar.add(Calendar.MONTH, -1)
                calendar.timeInMillis
            }
            StatisticsPeriod.QUARTER -> {
                calendar.add(Calendar.MONTH, -3)
                calendar.timeInMillis
            }
            StatisticsPeriod.YEAR -> {
                calendar.add(Calendar.YEAR, -1)
                calendar.timeInMillis
            }
            StatisticsPeriod.ALL -> 0L
        }
        
        return transactions.filter { it.date.time >= periodStartMillis }
    }
}

data class CategoryStatistic(
    val category: String,
    val amount: Double,
    val percentage: Double
)

data class MonthlyStatistic(
    val month: String,
    val income: Double,
    val expense: Double
)

enum class StatisticsPeriod(val displayName: String) {
    WEEK("一周"),
    MONTH("一个月"),
    QUARTER("三个月"),
    YEAR("一年"),
    ALL("全部")
}

class StatisticsViewModelFactory(private val repository: TransactionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatisticsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StatisticsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}