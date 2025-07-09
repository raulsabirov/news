package com.example.lib.flow

import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

class ParallelFlow {
}
fun main() = runBlocking {

    flow {
        emit(1)
        //   delay(100)
        emit(2)
        //   delay(100)
        emit(3)
    }

        .onEach {
            delay(5000)
            println(" delay end") }
        .collect { result ->
             println(result)
        }

}