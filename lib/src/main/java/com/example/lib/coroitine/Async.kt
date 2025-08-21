package com.example.lib.coroitine

import com.example.lib.handler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

/*
When async is used as a root coroutine
(coroutines that are a direct child of a CoroutineScope instance or supervisorScope),
exceptions are not thrown automatically, instead, they’re thrown when you call .await().
*/

val handler = CoroutineExceptionHandler { _, exception ->
    println("    CoroutineExceptionHandler got $exception")
}
val coroutineScope = CoroutineScope(Dispatchers.IO + handler)
// var forecast: Deferred<String> ? = null

fun main() = runBlocking {

/*    val asyncc = coroutineScope.async {
        throw RuntimeException("Error 1")
    }

    try {
           asyncc.await()
    } catch (_: Exception) {
        println( "catched error")
    }*/

    supervisorScope {
        val forecast = async() {
            throw RuntimeException("Error 2")
        }

        try {
            forecast.await()
        } catch (_: Exception) {

            println( "catched Error 2")
        }
        //   "catched error"
    }

    val temperature = async { 1 }

    println(temperature.await())
    //  println(result)


    /*    val job = coroutineScope.launch() {

            val deferred = async( SupervisorJob()) {
                val i = "s".toInt()
            }


          ///  val result = deferred.await()
            println("catch" )

        }

        job.join()*/
}

fun myFun() = runBlocking {

    suspend fun getForecast(): String {

        delay(1000)
        return "Sunny"
    }

    suspend fun getTemperature(): String {
        delay(1000)
        throw AssertionError("Temp is invalid")
        return "30 graduses"
    }


    // async в coroutineScope {} — исключение всплывёт, даже без await(),
    // потому что coroutineScope ждёт завершения всех корутин и обрабатывает ошибки.
    suspend fun getWeatherReport() = coroutineScope {
        val forecast = async { getForecast() }
        val temperature = async { getTemperature() }
        delay(200)
        forecast.await()
    }


    println(getWeatherReport())
}


fun myFunAsync() = coroutineScope.launch {

    val forecast = async {
        throw AssertionError("Temp is invalid")
    }
    val temperature = async { 1 }

    println(temperature.await())

}