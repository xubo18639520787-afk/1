package com.example.simpleaccounting.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleaccounting.viewmodel.MonthlyStatistic

@Composable
fun MonthlyBarChart(
    data: List<MonthlyStatistic>,
    modifier: Modifier = Modifier
) {
    if (data.isEmpty()) return
    
    val maxValue = data.maxOf { maxOf(it.income, it.expense) }
    
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height
                val barWidth = width / (data.size * 2 + 1)
                val bottomPadding = height * 0.1f
                
                // 绘制底线
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, height - bottomPadding),
                    end = Offset(width, height - bottomPadding),
                    strokeWidth = 2f
                )
                
                data.forEachIndexed { index, monthData ->
                    val startX = width * (index + 0.5f) / data.size
                    
                    // 收入柱状图
                    val incomeHeight = if (maxValue > 0) {
                        (height - bottomPadding) * (monthData.income / maxValue)
                    } else 0f
                    
                    drawRect(
                        color = Color(0xFF4CAF50),
                        topLeft = Offset(
                            startX - barWidth,
                            height - bottomPadding - incomeHeight
                        ),
                        size = Size(barWidth * 0.8f, incomeHeight)
                    )
                    
                    // 支出柱状图
                    val expenseHeight = if (maxValue > 0) {
                        (height - bottomPadding) * (monthData.expense / maxValue)
                    } else 0f
                    
                    drawRect(
                        color = Color(0xFFF44336),
                        topLeft = Offset(
                            startX,
                            height - bottomPadding - expenseHeight
                        ),
                        size = Size(barWidth * 0.8f, expenseHeight)
                    )
                }
            }
        }
        
        // 月份标签
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            data.forEach { monthData ->
                Text(
                    text = monthData.month,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(40.dp)
                )
            }
        }
        
        // 图例
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color(0xFF4CAF50), shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("收入", fontSize = 12.sp)
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color(0xFFF44336), shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("支出", fontSize = 12.sp)
        }
    }
}

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
