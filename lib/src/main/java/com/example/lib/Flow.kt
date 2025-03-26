package com.example.lib

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
        //sharedFlowExample()

       // flatMapConcatExample()
        combineExample()
    }

}

suspend fun sharedFlowExample() = coroutineScope {
    println("sharedFlowExample")
    val sharedFlow = MutableSharedFlow<Int>(replay = 0)

    launch {
        sharedFlow.emit(0)
        sharedFlow.emit(1)
        sharedFlow.emit(2)
        sharedFlow.emit(3)
        sharedFlow.emit(4)
    }

    delay(250)
    launch {
        sharedFlow.collect { value ->
            println("SharedFlow collector 1 received: $value")
        }
    }

    launch {
        delay(2500)
        sharedFlow.collect { value ->
            println("SharedFlow collector 2 received: $value")
        }
    }
}


suspend fun flatMapConcatExample() = coroutineScope {
    var i = 0
    val f : Flow<String>
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
    suspend fun testflatMapConcat(): String {
        delay(20000)
        return "C"
    }

    flowOf(1, 2, 3).flatMapConcat { number ->
        val i = 0
        flowOf("A", "B", testflatMapConcat()).apply {
            delay(100)

        }
    }.collect { result ->
        println(result)
    }
}

suspend fun flattenMergeExample() = coroutineScope {
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

suspend fun flatMapMergeExample() = coroutineScope {
    // flatMapMerge: Collects from multiple flows concurrently, merging their emissions as they come.
    flowOf(1, 2, 3).flatMapMerge { number ->
        flow {
            emit("$number: A")
            delay(100)
            emit("$number: B")
        }
    }
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
}

suspend fun combineExample() = coroutineScope {
    // The combine operator takes the latest value from each flow and emits a new value whenever
    // any of the flows emit a value. This is useful for cases where you want to react to the latest values from multiple flows.
    flow {
        emit(1)
        //   delay(100)
        emit(2)
        //   delay(100)
        emit(3)
    }.combine(
        flow {
            //   delay(50)
            emit("A")
            //   delay(150)
            emit("B")
            ///   delay(50)
            emit("C")
        })
    { number, letter ->
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

suspend fun flatMapLatestExample() = coroutineScope {
    // flatMapLatest: Cancels the previous flow whenever a new flow is emitted,
    // only collecting the latest emitted flow.
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


    /*



     */

}


class Flow(lifecycleScope: CoroutineScope) {
    val numbers = flowOf(1, 2, 3)

    val stateFlow = MutableStateFlow(1)

    suspend fun apiCall(value: Int): Int = withContext(Dispatchers.IO) {
        // stateFlow.stateIn()

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
            //  The zip operator combines two flows into one by pairing each emission from one flow with the corresponding
            //  emission from the other flow. The resulting flow emits values as pairs or as a transformation based on a
            //  provided lambda function. The combination stops as soon as one of the flows completes.
            flowOf(1, 2, 3)
                .zip(flowOf("A", "B", "C", "D"))
                { number, letter ->
                    "$number   $letter"
                }.collect { result ->
                    println(result)
                }
            // 1A,
            // 2B,
            // 3C
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
    }
}