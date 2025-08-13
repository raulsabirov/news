package com.example.lib.flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking<Unit> {
        sharedFlowExample()

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