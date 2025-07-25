package com.example.lib.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

 fun main() = runBlocking {
    flow {
        // Излучение данных будет происходить в потоке A
        println("Emit data on thread: ${Thread.currentThread().name}")
        emit(1)
    }
        .flowOn(Dispatchers.IO) // Поток A - Dispatchers.IO
        .onEach { value ->
            // Обработка данных будет происходить в потоке B
            println("Process data on thread: ${Thread.currentThread().name}")
        }

       // .emitOn(Dispatchers.Main) // Поток B - Dispatchers.Main
        .collect { value ->
            // Сбор данных будет происходить в потоке B (определяется emitOn)
            println("Collect data on thread: ${Thread.currentThread().name}")
        }
}