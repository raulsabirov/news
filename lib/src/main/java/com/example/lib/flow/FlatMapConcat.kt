package com.example.lib.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed



@OptIn(FlowPreview::class)
fun main() = runBlocking {
    var i = 0
    val f : Flow<String>
    // flatMapConcat: Concatenates flows sequentially,
    // waiting for each inner flow to complete before moving to the next.
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

        flowOf("A", "B", testflatMapConcat()).apply {
            delay(100)

        }
    }.collect { result ->
        println(result)
    }


    fun flowFrom(elem: String) = flowOf(1, 2, 3)
        .onEach { delay(1000) }
        .map { "${it}_${elem} " }

    suspend fun main() {
        flowOf("A", "B", "C")
            .flatMapConcat { flowFrom(it) }
            .collect { println(it) }
    }
    /*
        Output:-
        (1 sec)
        1_A
        (1 sec)
        2_A
        (1 sec)
        3_A
        (1 sec)
        1_B
        (1 sec)
        2_B
        (1 sec)
        3_B
        (1 sec)
        1_C
        (1 sec)
        2_C
        (1 sec)
     */
}