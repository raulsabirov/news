package com.example.news.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import kotlin.system.measureTimeMillis

@RunWith(AndroidJUnit4::class)
class RememberPerformanceTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Тестовые данные разных типов
    data class ComplexObject(
        val id: Int,
        val name: String,
        val data: List<String>,
        val metadata: Map<String, Any>
    )

    @Test
    fun testRememberWithPrimitiveTypes() {
        var recompositionCount = 0
        var rememberCalculationTime = 0L
        var directCalculationTime = 0L

        composeTestRule.setContent {
            TestRememberPrimitives(
                onRecomposition = { recompositionCount++ },
                onRememberTime = { rememberCalculationTime = it },
                onDirectTime = { directCalculationTime = it }
            )
        }

        // Вызываем рекомпозицию
        composeTestRule.onNodeWithText("Trigger Recomposition").performClick()
        composeTestRule.waitForIdle()

        println("=== Тест с примитивными типами ===")
        println("Количество рекомпозиций: $recompositionCount")
        println("Время с remember: ${rememberCalculationTime}ms")
        println("Время без remember: ${directCalculationTime}ms")
        println("Экономия времени: ${directCalculationTime - rememberCalculationTime}ms")
    }

    @Test
    fun testRememberWithComplexObjects() {
        var recompositionCount = 0
        var rememberCalculationTime = 0L
        var directCalculationTime = 0L

        composeTestRule.setContent {
            TestRememberComplexObjects(
                onRecomposition = { recompositionCount++ },
                onRememberTime = { rememberCalculationTime = it },
                onDirectTime = { directCalculationTime = it }
            )
        }

        // Вызываем рекомпозицию
        composeTestRule.onNodeWithText("Trigger Recomposition").performClick()
        composeTestRule.waitForIdle()

        println("=== Тест со сложными объектами ===")
        println("Количество рекомпозиций: $recompositionCount")
        println("Время с remember: ${rememberCalculationTime}ms")
        println("Время без remember: ${directCalculationTime}ms")
        println("Экономия времени: ${directCalculationTime - rememberCalculationTime}ms")
    }

    @Test
    fun testRememberWithLargeLists() {
        var recompositionCount = 0
        var rememberCalculationTime = 0L
        var directCalculationTime = 0L

        composeTestRule.setContent {
            TestRememberLargeLists(
                onRecomposition = { recompositionCount++ },
                onRememberTime = { rememberCalculationTime = it },
                onDirectTime = { directCalculationTime = it }
            )
        }

        // Вызываем рекомпозицию
        composeTestRule.onNodeWithText("Trigger Recomposition").performClick()
        composeTestRule.waitForIdle()

        println("=== Тест с большими списками ===")
        println("Количество рекомпозиций: $recompositionCount")
        println("Время с remember: ${rememberCalculationTime}ms")
        println("Время без remember: ${directCalculationTime}ms")
        println("Экономия времени: ${directCalculationTime - rememberCalculationTime}ms")
    }


    @Test
    fun testRememberWithExpensiveCalculations() {
        var recompositionCount = 0
        var rememberCalculationTime = 0L
        var directCalculationTime = 0L

        composeTestRule.setContent {
            TestRememberExpensiveCalculations(
                onRecomposition = { recompositionCount++ },
                onRememberTime = { rememberCalculationTime = it },
                onDirectTime = { directCalculationTime = it }
            )
        }

        // Вызываем рекомпозицию
        composeTestRule.onNodeWithText("Trigger Recomposition").performClick()
        composeTestRule.waitForIdle()

        println("=== Тест с дорогими вычислениями ===")
        println("Количество рекомпозиций: $recompositionCount")
        println("Время с remember: ${rememberCalculationTime}ms")
        println("Время без remember: ${directCalculationTime}ms")
        println("Экономия времени: ${directCalculationTime - rememberCalculationTime}ms")
    }

    @Composable
    private fun TestRememberPrimitives(
        onRecomposition: () -> Unit,
        onRememberTime: (Long) -> Unit,
        onDirectTime: (Long) -> Unit
    ) {
        var counter by remember { mutableStateOf(0) }
        onRecomposition()

        // Тест с remember
        val rememberedValue = remember {
            val time = measureTimeMillis {
                // Имитация вычислений с примитивными типами
                var result = 0
                repeat(10000) { i ->
                    result += i * 2
                }
            }
            onRememberTime(time)
            "Calculated: ${counter * 100}"
        }

        // Тест без remember
        val directValue = run {
            val time = measureTimeMillis {
                // Те же вычисления без remember
                var result = 0
                repeat(10000) { i ->
                    result += i * 2
                }
            }
            onDirectTime(time)
            "Calculated: ${counter * 100}"
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Remember: $rememberedValue")
            Text("Direct: $directValue")
            Button(onClick = { counter++ }) {
                Text("Trigger Recomposition")
            }
        }
    }

    @Composable
    private fun TestRememberComplexObjects(
        onRecomposition: () -> Unit,
        onRememberTime: (Long) -> Unit,
        onDirectTime: (Long) -> Unit
    ) {
        var counter by remember { mutableStateOf(0) }
        onRecomposition()

        // Тест с remember
        val rememberedObject = remember {
            val time = measureTimeMillis {
                ComplexObject(
                    id = counter,
                    name = "Object $counter",
                    data = (1..1000).map { "Item $it" },
                    metadata = (1..100).associate { "key$it" to "value$it" }
                )
            }
            onRememberTime(time)
            time
        }

        // Тест без remember
        val directObject = run {
            val time = measureTimeMillis {
                ComplexObject(
                    id = counter,
                    name = "Object $counter",
                    data = (1..1000).map { "Item $it" },
                    metadata = (1..100).associate { "key$it" to "value$it" }
                )
            }
            onDirectTime(time)
            time
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Remember time: ${rememberedObject}ms")
            Text("Direct time: ${directObject}ms")
            Button(onClick = { counter++ }) {
                Text("Trigger Recomposition")
            }
        }
    }

    @Composable
    private fun TestRememberLargeLists(
        onRecomposition: () -> Unit,
        onRememberTime: (Long) -> Unit,
        onDirectTime: (Long) -> Unit
    ) {
        var counter by remember { mutableStateOf(0) }
        onRecomposition()

        // Тест с remember
        val rememberedList = remember {
            val time = measureTimeMillis {
                (1..10000).map { index ->
                    ComplexObject(
                        id = index,
                        name = "Item $index",
                        data = (1..10).map { "Data $it" },
                        metadata = mapOf("index" to index, "counter" to counter)
                    )
                }
            }
            onRememberTime(time)
            time
        }

        // Тест без remember
        val directList = run {
            val time = measureTimeMillis {
                (1..10000).map { index ->
                    ComplexObject(
                        id = index,
                        name = "Item $index",
                        data = (1..10).map { "Data $it" },
                        metadata = mapOf("index" to index, "counter" to counter)
                    )
                }
            }
            onDirectTime(time)
            time
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Remember list creation: ${rememberedList}ms")
            Text("Direct list creation: ${directList}ms")
            Button(onClick = { counter++ }) {
                Text("Trigger Recomposition")
            }
        }
    }

    @Composable
    private fun TestRememberExpensiveCalculations(
        onRecomposition: () -> Unit,
        onRememberTime: (Long) -> Unit,
        onDirectTime: (Long) -> Unit
    ) {
        var counter by remember { mutableStateOf(0) }
        onRecomposition()

        // Дорогая функция для тестирования
        fun expensiveCalculation(): String {
            var result = 0L
            repeat(100000) { i ->
                result += (i * i).toLong()
                if (i % 1000 == 0) {
                    result = result.toString().length.toLong()
                }
            }
            return "Result: $result"
        }

        // Тест с remember
        val rememberedResult = remember {
            val time = measureTimeMillis {
                expensiveCalculation()
            }
            onRememberTime(time)
            time
        }

        // Тест без remember
        val directResult = run {
            val time = measureTimeMillis {
                expensiveCalculation()
            }
            onDirectTime(time)
            time
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Remember calculation: ${rememberedResult}ms")
            Text("Direct calculation: ${directResult}ms")
            Button(onClick = { counter++ }) {
                Text("Trigger Recomposition")
            }
        }
    }
}
