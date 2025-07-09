package com.example.lib.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
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