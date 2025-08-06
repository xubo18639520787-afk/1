package com.example.simpleaccounting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simpleaccounting.data.AppDatabase
import com.example.simpleaccounting.repository.TransactionRepository
import com.example.simpleaccounting.ui.screen.MainScreen
import com.example.simpleaccounting.ui.screen.StatisticsScreen
import com.example.simpleaccounting.ui.theme.SimpleAccountingTheme
import com.example.simpleaccounting.viewmodel.MainViewModel
import com.example.simpleaccounting.viewmodel.MainViewModelFactory
import com.example.simpleaccounting.viewmodel.StatisticsViewModel
import com.example.simpleaccounting.viewmodel.StatisticsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = AppDatabase.getDatabase(this)
        val repository = TransactionRepository(database)
        val mainViewModelFactory = MainViewModelFactory(repository)
        val statisticsViewModelFactory = StatisticsViewModelFactory(repository)
        
        setContent {
            SimpleAccountingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf(Screen.MAIN) }
                    
                    when (currentScreen) {
                        Screen.MAIN -> {
                            val viewModel: MainViewModel = viewModel(factory = mainViewModelFactory)
                            MainScreen(
                                viewModel = viewModel,
                                onNavigateToStatistics = { currentScreen = Screen.STATISTICS }
                            )
                        }
                        Screen.STATISTICS -> {
                            val viewModel: StatisticsViewModel = viewModel(factory = statisticsViewModelFactory)
                            StatisticsScreen(
                                viewModel = viewModel,
                                onBackClick = { currentScreen = Screen.MAIN }
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class Screen {
    MAIN,
    STATISTICS
}
