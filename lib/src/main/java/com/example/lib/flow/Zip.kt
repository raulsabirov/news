package com.example.lib.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed

fun main() = runBlocking {
    //  The zip operator combines two flows into one by pairing each emission from one flow with the corresponding
    //  emission from the other flow. The resulting flow emits values as pairs or as a transformation based on a

    val time = now()
    flow {
        delay(1000)
        emit(1)
        //   delay(100)
        emit(2)
        //   delay(100)
        emit(3)
    }
        .zip(    flow {
            delay(1000)
            emit("A")

            //   delay(100)
            emit("B")
            //   delay(100)
            emit("C")
        })
        { number, letter ->
            "$number   $letter"
        }.collect { result ->
            println(result)
        }

    print(  " ${time.passed}")
}