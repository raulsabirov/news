package com.example.lib.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed


private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char->")
        delay(1000)
    }
}

//Calling flatMapMerge() on flow1 combines each element of flow1 with every element of flow2,
// similar to flatMapConcat(). However, unlike flatMapConcat(), flatMapMerge() operates concurrently,
// allowing the processing of elements from both flows concurrently.

@OptIn(FlowPreview::class)
fun main() = runBlocking {
    val time = now()
    var result = ""
    stringFlow().flatMapMerge { value ->
        flow {
            //   withContext(Dispatchers.IO) {
            delay(1000)
            emit(value)

            //    }
        }
    }.collect { item ->
        result+=item
    }

    print(result + " ${time.passed}")
}