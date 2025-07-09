package com.example.lib.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
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
}