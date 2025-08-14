package com.example.lib.coroitine

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("Handle $exception in CoroutineExceptionHandler")
    }

    val topLevelScope = CoroutineScope(Job())

    topLevelScope.launch(coroutineExceptionHandler) {
        launch() {
            throw RuntimeException("RuntimeException in nested coroutine")
        }
    }.invokeOnCompletion {

    }

    Thread.sleep(100)
}