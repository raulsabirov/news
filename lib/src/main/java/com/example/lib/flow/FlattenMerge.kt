package com.example.lib.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import utils.now
import utils.passed

fun main() = runBlocking {
    val time = now()
    var result = ""

    val flow1 = flow {
        delay(1000)
        emit("A")
    }

    val flow2 = flow {
        delay(500)
        emit("B")
    }

    val flow3 = flow {
        delay(1500)
        emit("C")
    }

    //The flattenMerge operator collects from multiple flows concurrently and merges their emissions into a single flow.
    // This is useful when you want to start collecting from multiple flows simultaneously without waiting for one to complete before starting the next.
    listOf(flow1, flow2, flow3).asFlow().flattenMerge().collect { value ->
        println(value)
    }


    // Output: B, A, C

    print(result + " ${time.passed}")
}
