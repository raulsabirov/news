package com.example.lib

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip

import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


//val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

fun main() {

    runBlocking<Unit> {

    }

}





suspend fun flatMapLatestExample() = coroutineScope {

}



suspend fun zipExample() = coroutineScope {

}




class Flow(lifecycleScope: CoroutineScope) {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    val coroutineScope = CoroutineScope( Dispatchers.IO   + handler)


    val numbers = flowOf(1, 2, 3)

    val stateFlow = MutableStateFlow(1)

    suspend fun apiCall(value: Int): Int = withContext(Dispatchers.IO) {
        // stateFlow.stateIn()

        delay(100)
        value - 4
    }

    fun events(): Flow<Int> = (1..3)
        .asFlow()
        .filter {  it > 2  }
        .onEach { delay(100) }

    init {
        stateFlow.value = 1

        numbers.map {
            apiCall(it)
        }

        runBlocking<Unit> {

        }

        runBlocking<Unit> {
            val flow1 = flow {
                emit("A")
                delay(100)
                emit("B")
            }

            val flow2 = flow {
                delay(50)
                emit("1")
                delay(50)
                emit("2")
            }

            // The merge operator combines multiple flows by interleaving their emissions without pairing them.
            // It collects from each flow as they emit and emits each value in the order it’s produced.
            merge(flow1, flow2).collect { value ->
                println(value)
            }
            // Output
            // A
            // 1
            // B
            // 2
        }



        runBlocking {
            val flow = flow {
                emit(1)
                emit(2)
                emit(3)
            }

            flow
                .onEach { println("Emitting $it on thread: ${Thread.currentThread().name}") }
                .stateIn(coroutineScope)
                .collect { value ->
                    withContext(Dispatchers.IO) {
                        println("Collected $value on thread: ${Thread.currentThread().name}")
                    }
                }
        }



        flow {
            emit(1)
            delay(90)
            emit(2)
            delay(90)
            emit(3)
            delay(1010)
            emit(4)
            delay(1010)
            emit(5)
        }.debounce(1000)
    }

}