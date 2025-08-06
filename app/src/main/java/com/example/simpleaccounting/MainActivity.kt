package com.example.simpleaccounting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simpleaccounting.data.AppDatabase
import com.example.simpleaccounting.repository.TransactionRepository
import com.example.simpleaccounting.ui.screen.MainScreen
import com.example.simpleaccounting.ui.theme.SimpleAccountingTheme
import com.example.simpleaccounting.viewmodel.MainViewModel
import com.example.simpleaccounting.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = AppDatabase.getDatabase(this)
        val repository = TransactionRepository(database)
        val viewModelFactory = MainViewModelFactory(repository)
        
        setContent {
            SimpleAccountingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}