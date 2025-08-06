package com.example.simpleaccounting.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.simpleaccounting.viewmodel.CategoryStatistic
import kotlin.math.abs
import kotlin.math.min

@Composable
fun CategoryPieChart(
    data: List<CategoryStatistic>,
    modifier: Modifier = Modifier
) {
    if (data.isEmpty()) return
    
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val radius = min(width, height) / 2 * 0.8f
            val center = Offset(width / 2, height / 2)
            
            var startAngle = 0f
            
            data.forEach { (category, _, percentage) ->
                val sweepAngle = percentage.toFloat() * 360f
                
                // 绘制扇形
                drawArc(
                    color = getCategoryColor(category),
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2)
                )
                
                // 绘制边框
                drawArc(
                    color = Color.White,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2),
                    style = Stroke(width = 2f)
                )
                
                startAngle += sweepAngle
            }
        }
    }
}

// 为了简化示例，这里使用一个简单的函数来生成分类颜色
private fun getCategoryColor(category: String): Color {
    val colors = listOf(
        Color(0xFFE57373), Color(0xFF81C784), Color(0xFF64B5F6),
        Color(0xFFFFB74D), Color(0xFFBA68C8), Color(0xFF4DB6AC),
        Color(0xFFFF8A65), Color(0xFF9575CD), Color(0xFFF06292),
        Color(0xFFFFD54F)
    )
    
    return colors[abs(category.hashCode()) % colors.size]
}