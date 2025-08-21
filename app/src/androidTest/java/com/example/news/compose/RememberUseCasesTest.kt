package com.example.news.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
class RememberUseCasesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRememberWithDerivedState() {
        var calculationCount = 0
        
        composeTestRule.setContent {
            TestDerivedStatePerformance { calculationCount++ }
        }

        // Вызываем несколько рекомпозиций
        repeat(5) {
            composeTestRule.onNodeWithText("Add Item").performClick()
            composeTestRule.waitForIdle()
        }

        println("=== Тест derivedStateOf ===")
        println("Количество пересчетов: $calculationCount")
        println("Ожидаемое количество: 5 (по одному на каждое изменение)")
    }

    @Test
    fun testRememberWithLazyListState() {
        var recompositionCount = 0
        
        composeTestRule.setContent {
            TestLazyListPerformance { recompositionCount++ }
        }

        // Добавляем элементы в список
        repeat(10) {
            composeTestRule.onNodeWithText("Add Item").performClick()
            composeTestRule.waitForIdle()
        }

        println("=== Тест LazyList с remember ===")
        println("Количество рекомпозиций: $recompositionCount")
    }

    @Test
    fun testRememberWithExpensiveFiltering() {
        var filterCalculationTime = 0L
        
        composeTestRule.setContent {
            TestExpensiveFiltering { time ->
                filterCalculationTime = time
            }
        }

        // Изменяем фильтр
        composeTestRule.onNodeWithText("Change Filter").performClick()
        composeTestRule.waitForIdle()

        println("=== Тест дорогой фильтрации ===")
        println("Время фильтрации: ${filterCalculationTime}ms")
    }

    @Test
    fun testRememberWithObjectCreation() {
        var objectCreationCount = 0
        
        composeTestRule.setContent {
            TestObjectCreation { objectCreationCount++ }
        }

        // Вызываем рекомпозицию
        repeat(3) {
            composeTestRule.onNodeWithText("Trigger Recomposition").performClick()
            composeTestRule.waitForIdle()
        }

        println("=== Тест создания объектов ===")
        println("Количество созданий объектов: $objectCreationCount")
        println("Ожидаемое количество: 1 (объект должен создаваться только один раз)")
    }

    @Composable
    private fun TestDerivedStatePerformance(
        onCalculation: () -> Unit
    ) {
        var items by remember { mutableStateOf(listOf<String>()) }
        
        // Используем derivedStateOf для дорогого вычисления
        val expensiveCalculation by remember {
            derivedStateOf {
                onCalculation()
                // Имитация дорогого вычисления
                items.filter { it.length > 5 }
                    .map { it.uppercase() }
                    .sortedDescending()
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Items count: ${items.size}")
            Text("Filtered count: ${expensiveCalculation.size}")
            
            Button(onClick = { 
                items = items + "Item ${items.size + 1} with long name"
            }) {
                Text("Add Item")
            }
        }
    }

    @Composable
    private fun TestLazyListPerformance(
        onRecomposition: () -> Unit
    ) {
        var items by remember { mutableStateOf(listOf<String>()) }
        onRecomposition()
        
        // Remember дорогое преобразование данных для LazyList
        val processedItems = remember(items) {
            items.mapIndexed { index, item ->
                ProcessedItem(
                    id = index,
                    title = item,
                    subtitle = "Processed: ${item.reversed()}",
                    metadata = generateMetadata(index)
                )
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Items: ${items.size}")
            
            LazyColumn {
                items(processedItems) { item ->
                    Column {
                        Text("${item.id}: ${item.title}")
                        Text(item.subtitle)
                    }
                }
            }
            
            Button(onClick = { 
                items = items + "Item ${items.size + 1}"
            }) {
                Text("Add Item")
            }
        }
    }

    @Composable
    private fun TestExpensiveFiltering(
        onFilterTime: (Long) -> Unit
    ) {
        var filterQuery by remember { mutableStateOf("") }
        
        val largeDataSet = remember {
            (1..5000).map { "Item $it with some description and metadata" }
        }
        
        val filteredData = remember(filterQuery) {
            val time = measureTimeMillis {
                largeDataSet.filter { item ->
                    item.contains(filterQuery, ignoreCase = true)
                }.take(100) // Ограничиваем результат для UI
            }
            onFilterTime(time)
            largeDataSet.filter { it.contains(filterQuery, ignoreCase = true) }.take(100)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Filter: '$filterQuery'")
            Text("Results: ${filteredData.size}")
            
            LazyColumn {
                items(filteredData) { item ->
                    Text(item)
                }
            }
            
            Button(onClick = { 
                filterQuery = if (filterQuery.isEmpty()) "Item 1" else ""
            }) {
                Text("Change Filter")
            }
        }
    }

    @Composable
    private fun TestObjectCreation(
        onObjectCreated: () -> Unit
    ) {
        var counter by remember { mutableStateOf(0) }
        
        // Дорогой объект, который должен создаваться только один раз
        val expensiveObject = remember {
            onObjectCreated()
            ExpensiveObject(
                data = (1..1000).map { "Data $it" },
                calculations = (1..1000).associate { it to it * it },
                timestamp = System.currentTimeMillis()
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Counter: $counter")
            Text("Object created at: ${expensiveObject.timestamp}")
            Text("Data size: ${expensiveObject.data.size}")
            
            Button(onClick = { counter++ }) {
                Text("Trigger Recomposition")
            }
        }
    }

    private fun generateMetadata(index: Int): Map<String, Any> {
        return mapOf(
            "index" to index,
            "timestamp" to System.currentTimeMillis(),
            "hash" to index.hashCode(),
            "data" to (1..10).map { "meta$it" }
        )
    }

    data class ProcessedItem(
        val id: Int,
        val title: String,
        val subtitle: String,
        val metadata: Map<String, Any>
    )

    data class ExpensiveObject(
        val data: List<String>,
        val calculations: Map<Int, Int>,
        val timestamp: Long
    )
}
