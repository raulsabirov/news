package com.example.lib.flow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    flow {
        emit(1)
        throw RuntimeException("Произошла ошибка")
    }.catch { e: Throwable ->
        // Обработка ошибки
        emit(-1) // Выбросить другое значение при ошибке
    }.onCompletion {
        println("onCompletion")
    }
        .collect { value ->
        println(value)
    }
}