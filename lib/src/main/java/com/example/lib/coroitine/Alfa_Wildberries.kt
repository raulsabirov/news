package com.example.lib.coroitine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

var myInnerJob: Job? = null
var myOuterJob: Job? = null
val scope = CoroutineScope(Dispatchers.Default)

fun main() = runBlocking {
    scope.launch {
        myOuterJob = launch(Dispatchers.IO) {
            myInnerJob = launch(Job()) {
                while (true) {
                    delay(1000L)
                }
            }
            while (true) {
                delay(1000L)
            }
        }
    }

    delay(2000L)
    scope.cancel()
    delay(1000L)
    println(myOuterJob?.isActive)
    println(myInnerJob?.isActive)
}