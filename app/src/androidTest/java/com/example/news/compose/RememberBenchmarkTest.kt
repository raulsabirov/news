package com.example.news.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.system.measureNanoTime

@RunWith(AndroidJUnit4::class)
class RememberBenchmarkTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        private const val ITERATIONS = 100
        private const val WARMUP_ITERATIONS = 10
    }

    @Test
    fun benchmarkRememberVsDirectCalculation() {
        val results = mutableListOf<BenchmarkResult>()

        composeTestRule.setContent {
            BenchmarkTestComposable { result ->
                results.add(result)
            }
        }

        // Прогрев
        repeat(WARMUP_ITERATIONS) {
            composeTestRule.onNodeWithText("Run Benchmark").performClick()
            composeTestRule.waitForIdle()
        }

        results.clear()

        // Основные измерения
        repeat(ITERATIONS) {
            composeTestRule.onNodeWithText("Run Benchmark").performClick()
            composeTestRule.waitForIdle()
        }

        // Анализ результатов
        analyzeResults(results)
    }

    @Test
    fun benchmarkRememberWithDifferentDataSizes() {
        val dataSizes = listOf(10, 100, 1000, 5000)
        
        dataSizes.forEach { size ->
            val results = mutableListOf<BenchmarkResult>()
            
            composeTestRule.setContent {
                BenchmarkDataSizeComposable(dataSize = size) { result ->
                    results.add(result)
                }
            }

            // Прогрев
            repeat(WARMUP_ITERATIONS) {
                composeTestRule.onNodeWithText("Run Benchmark $size").performClick()
                composeTestRule.waitForIdle()
            }

            results.clear()

            // Основные измерения
            repeat(ITERATIONS) {
                composeTestRule.onNodeWithText("Run Benchmark $size").performClick()
                composeTestRule.waitForIdle()
            }

            println("=== Результаты для размера данных: $size ===")
            analyzeResults(results)
        }
    }

    @Test
    fun benchmarkRememberWithComplexityLevels() {
        val complexityLevels = listOf(1, 5, 10, 15)
        
        complexityLevels.forEach { complexity ->
            val results = mutableListOf<BenchmarkResult>()
            
            composeTestRule.setContent {
                BenchmarkComplexityComposable(complexity = complexity) { result ->
                    results.add(result)
                }
            }

            // Прогрев
            repeat(WARMUP_ITERATIONS) {
                composeTestRule.onNodeWithText("Run Complexity $complexity").performClick()
                composeTestRule.waitForIdle()
            }

            results.clear()

            // Основные измерения
            repeat(ITERATIONS) {
                composeTestRule.onNodeWithText("Run Complexity $complexity").performClick()
                composeTestRule.waitForIdle()
            }

            println("=== Результаты для уровня сложности: $complexity ===")
            analyzeResults(results)
        }
    }

    private fun analyzeResults(results: List<BenchmarkResult>) {
        if (results.isEmpty()) return

        val rememberTimes = results.map { it.rememberTime }
        val directTimes = results.map { it.directTime }

        val avgRemember = rememberTimes.average()
        val avgDirect = directTimes.average()
        val medianRemember = rememberTimes.sorted()[rememberTimes.size / 2]
        val medianDirect = directTimes.sorted()[directTimes.size / 2]

        val improvement = if (avgDirect > 0) ((avgDirect - avgRemember) / avgDirect * 100) else 0.0

        println("Среднее время с remember: ${avgRemember.toLong()} нс")
        println("Среднее время без remember: ${avgDirect.toLong()} нс")
        println("Медианное время с remember: $medianRemember нс")
        println("Медианное время без remember: $medianDirect нс")
        println("Улучшение производительности: ${improvement.toInt()}%")
        println("Экономия времени: ${(avgDirect - avgRemember).toLong()} нс")
        println("---")
    }

    data class BenchmarkResult(
        val rememberTime: Long,
        val directTime: Long
    )

    @Composable
    private fun BenchmarkTestComposable(
        onResult: (BenchmarkResult) -> Unit
    ) {
        var counter by remember { mutableStateOf(0) }

        // Тест с remember
        val rememberTime = measureNanoTime {
            remember(counter) {
                // Имитация вычислений
                var result = 0
                repeat(1000) { i ->
                    result += i * counter
                }
                result
            }
        }

        // Тест без remember
        val directTime = measureNanoTime {
            var result = 0
            repeat(1000) { i ->
                result += i * counter
            }
            result
        }

        onResult(BenchmarkResult(rememberTime, directTime))

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Counter: $counter")
            Text("Remember time: $rememberTime нс")
            Text("Direct time: $directTime нс")
            Button(onClick = { counter++ }) {
                Text("Run Benchmark")
            }
        }
    }

    @Composable
    private fun BenchmarkDataSizeComposable(
        dataSize: Int,
        onResult: (BenchmarkResult) -> Unit
    ) {
        var counter by remember { mutableStateOf(0) }

        // Тест с remember
        val rememberTime = measureNanoTime {
            remember(counter) {
                (1..dataSize).map { index ->
                    "Item $index with counter $counter"
                }
            }
        }

        // Тест без remember
        val directTime = measureNanoTime {
            (1..dataSize).map { index ->
                "Item $index with counter $counter"
            }
        }

        onResult(BenchmarkResult(rememberTime, directTime))

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Data size: $dataSize")
            Text("Counter: $counter")
            Button(onClick = { counter++ }) {
                Text("Run Benchmark $dataSize")
            }
        }
    }

    @Composable
    private fun BenchmarkComplexityComposable(
        complexity: Int,
        onResult: (BenchmarkResult) -> Unit
    ) {
        var counter by remember { mutableStateOf(0) }

        fun complexCalculation(): Map<String, Any> {
            val result = mutableMapOf<String, Any>()
            repeat(complexity * 50) { i ->
                result["key$i"] = (i * counter).toString()
                if (i % 5 == 0) {
                    result["nested$i"] = mapOf(
                        "value" to i,
                        "counter" to counter,
                        "data" to (1..complexity).toList()
                    )
                }
            }
            return result
        }

        // Тест с remember
        val rememberTime = measureNanoTime {
            remember(counter) {
                complexCalculation()
            }
        }

        // Тест без remember
        val directTime = measureNanoTime {
            complexCalculation()
        }

        onResult(BenchmarkResult(rememberTime, directTime))

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Complexity: $complexity")
            Text("Counter: $counter")
            Button(onClick = { counter++ }) {
                Text("Run Complexity $complexity")
            }
        }
    }
}
