package com.example.simpleaccounting.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.simpleaccounting.data.TransactionType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionDialog(
    onDismiss: () -> Unit,
    onConfirm: (Double, String, String, TransactionType) -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(TransactionType.EXPENSE) }
    
    val commonCategories = listOf(
        "餐饮", "交通", "购物", "娱乐", "医疗", "教育", 
        "工资", "奖金", "投资", "其他"
    )
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("添加交易") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 交易类型选择
                Text("交易类型")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = selectedType == TransactionType.EXPENSE,
                                onClick = { selectedType = TransactionType.EXPENSE }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedType == TransactionType.EXPENSE,
                            onClick = { selectedType = TransactionType.EXPENSE }
                        )
                        Text("支出")
                    }
                    
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = selectedType == TransactionType.INCOME,
                                onClick = { selectedType = TransactionType.INCOME }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedType == TransactionType.INCOME,
                            onClick = { selectedType = TransactionType.INCOME }
                        )
                        Text("收入")
                    }
                }
                
                // 金额输入
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("金额") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
                
                // 描述输入
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("描述") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                // 分类输入
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("分类") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                // 常用分类快捷选择
                Text("常用分类", style = MaterialTheme.typography.bodySmall)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(commonCategories.size) { index ->
                        FilterChip(
                            onClick = { category = commonCategories[index] },
                            label = { Text(commonCategories[index]) },
                            selected = category == commonCategories[index]
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val amountValue = amount.toDoubleOrNull()
                    if (amountValue != null && amountValue > 0 && 
                        description.isNotBlank() && category.isNotBlank()) {
                        onConfirm(amountValue, description, category, selectedType)
                    }
                },
                enabled = amount.toDoubleOrNull() != null && 
                         amount.toDoubleOrNull()!! > 0 &&
                         description.isNotBlank() && 
                         category.isNotBlank()
            ) {
                Text("确定")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消")
            }
        }
    )
}

@Composable
private fun LazyRow(
    horizontalArrangement: Arrangement.Horizontal,
    content: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = horizontalArrangement
    ) {
        content()
    }
}

@Composable
private fun items(size: Int, itemContent: @Composable (Int) -> Unit) {
    repeat(size) { index ->
        itemContent(index)
    }
}