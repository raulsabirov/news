package com.example.lib.flow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flow {
        emit(1)
        emit(2)
        throw RuntimeException("Ошибка после 2")
        emit(3)
    }.onEach { value ->
        try {
            // Обработка каждого элемента
            if (value == 2) throw IllegalArgumentException("Невалидное значение 2")
        } catch (e: Throwable) {
            // Обработка ошибки для конкретного value
            println("Ошибка для значения $value: ${e.message}")
            // Можно пробросить исключение дальше, если нужно остановить поток
          //  throw e
        }
    }.catch { e: Throwable ->
        // Обработка ошибок, не перехваченных в onEach, или ошибок, брошенных после onEach
        println("Итоговая ошибка: ${e.message}")
    }.collect { value ->
        println("Собрано значение: $value")
    }
}