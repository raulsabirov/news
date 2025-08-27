package com.example.lib.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.runBlocking
import java.io.IOException

class FlowRetry {
}

fun main() = runBlocking {


    flow {
        emit(1)
        throw IOException("Network error")
    }.retry(3) { throwable ->
        throwable is IOException // Retry only on IOException
    }.collect { value ->
        println("Collected: $value")
    }

    flow {
        emit(1)
        throw IOException("Network error")
    }.retryWhen { cause, attempt ->
        if (cause is IOException && attempt < 3) {
            delay(1000 * attempt) // Exponential backoff
            true // Retry
        } else {
            false // Do not retry
        }
    }.collect { value ->
        println("Collected: $value")
    }
}