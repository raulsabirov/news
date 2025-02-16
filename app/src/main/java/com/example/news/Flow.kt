package com.example.news

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.isActive
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip

import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.lang.System.currentTimeMillis


class Flow(lifecycleScope: CoroutineScope) {

    val numbers1  : Flow<Int> = flow {

    }

    val numbers = flowOf(1, 2, 3)

    fun delayedFlow() = flow {
        emit("A")
        delay(1000)
        emit("B")
    }

    fun requestFlow(i: Int): Flow<String> = flow {
        emit("$i: First")
        delay(500) // wait 500 ms
        emit("$i: Second")
    }

    fun helperFlow(i: Int): Flow<String> = flow {
        while (currentCoroutineContext().isActive){
            emit("$i: helperFlow")
            delay(1000) // wait 50
        }
    }


    val flowOfFlows = flowOf(
        flow {
            emit("A1")
            delay(100)
            emit("A2")
        },
        flow {
            emit("B1")
            delay(50)
            emit("B2")
        }
    )

    val  stateFlow = MutableStateFlow(1)

    suspend fun apiCall(value: Int): Int = withContext(Dispatchers.IO) {
        stateFlow.value = 1

        delay(100)
        value - 4
    }

    fun events(): Flow<Int> = (1..3)
        .asFlow()
        .onEach { delay(100) }

    init {
        stateFlow.value = 1

        numbers.map {
            apiCall(it)
        }


        runBlocking<Unit> {


            numbers1
            val flow1 = flowOf(1, 2, 3)
            val flow2 = flowOf("A", "B", "C", "D")

            //  The zip operator combines two flows into one by pairing each emission from one flow with the corresponding
            //  emission from the other flow. The resulting flow emits values as pairs or as a transformation based on a
            //  provided lambda function. The combination stops as soon as one of the flows completes.
            flow1.zip(flow2) { number, letter ->
                "$number$letter"
            }.collect { result ->
                println(result)
            }
            // 1A,
            // 2B,
            // 3C
        }

        runBlocking<Unit> {
            val flow1 = flow {
                emit(1)
                delay(100)
                emit(2)
                delay(100)
                emit(3)
            }

            val flow2 = flow {
                delay(50)
                emit("A")
                delay(150)
                emit("B")
                delay(50)
                emit("C")
            }

            // The combine operator takes the latest value from each flow and emits a new value whenever
            // any of the flows emit a value. This is useful for cases where you want to react to the latest values from multiple flows.
            flow1.combine(flow2) { number, letter ->
                "$number$letter"
            }.collect { result ->
                println(result)
            }
            // Output:
            // 1A
            // 2A
            // 2B
            // 3B
            // 3C
        }

        runBlocking<Unit> {

            val flow1 = flow {
                delay(100)
                emit("A")
            }

            val flow2 = flow {
                delay(50)
                emit("B")
            }

            val flow3 = flow {
                delay(150)
                emit(1)
            }

            //The flattenMerge operator collects from multiple flows concurrently and merges their emissions into a single flow.
            // This is useful when you want to start collecting from multiple flows simultaneously without waiting for one to complete before starting the next.
            listOf(flow1, flow2, flow3).asFlow().flattenMerge().collect { value ->
                println(value)
            }
            // Output: B, A, C

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

        runBlocking<Unit> {
            // flatMapConcat: Concatenates flows sequentially, waiting for each inner flow to complete before moving to the next.
            flowOf(1, 2, 3).flatMapConcat { number ->
                flow {
                    emit("$number: A")
                    delay(100)
                    emit("$number: B")
                }
            }.collect { result ->
                println(result)
            }
            // Output:
            // 1: A
            // 1: B
            // 2: A
            // 2: B
            // 3: A
            // 3: B


            // flatMapMerge: Collects from multiple flows concurrently, merging their emissions as they come.
            flowOf(1, 2, 3).flatMapMerge { number ->
                flow {
                    emit("$number: A")
                    delay(100)
                    emit("$number: B")
                }
            }
                .buffer(3)
                .collect { result ->
                println(result)
            }
            // Output:
            // 1: A
            // 2: A
            // 3: A
            // 1: B
            // 2: B
            // 3: B

/*            runBlocking<Unit> {
                someFlow().flatMapMerge {
                    helperFlow(it)
                }.collect {
                    println(it)
                }
            }*/



            // flatMapLatest: Cancels the previous flow whenever a new flow is emitted, only collecting the latest emitted flow.
            flowOf(1, 2, 3).flatMapLatest { number ->
                flow {
                    emit("$number: A")
                    delay(100)
                    emit("$number: B")
                }
            }.collect { result ->
                println(result)
            }
            // Output:
            // 1: A
            // 2: A
            // 3: A
            // 3: B
        }

        runBlocking {
            val flow = flow {
                emit(1)
                emit(2)
                emit(3)
            }

            flow
                .onEach { println("Emitting $it on thread: ${Thread.currentThread().name}") }
                .collect { value ->
                    withContext(Dispatchers.IO) {
                        println("Collected $value on thread: ${Thread.currentThread().name}")
                    }
                }
        }
    }
}