package com.example.lib

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

val coroutineScope = CoroutineScope(Job() + Dispatchers.IO + NonCancellable)


fun main() {

    val map = mutableMapOf(1 to 1, 1 to 2)
    println(map)

    var i =0
    repeat(100000){
        test( {
           // return@test
            i
        })

        i++
    }
}

fun test(  cl: () ->Int){
    println(cl())
    println("free  " + Runtime.getRuntime().freeMemory())
}


class Coroutines(lifecycleScope: CoroutineScope) {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    init {
        //  lifecycleScope.cancel()
        GlobalScope
        coroutineScope.launch {
            coroutineScope.launch(handler) {
               this@launch
                throw  RuntimeException("RuntimeException")
            }


        }

        val job = lifecycleScope.launch(handler + Dispatchers.Default) {

            val scope =  coroutineScope {
                this
            }


            delay(100)

            val deferred = async( SupervisorJob()) {
                val i = "s".toInt()
            }

            // ...

            val result = deferred.await()
            println("catch" + result)

            try {
                val i = "s".toInt()
            } catch (e: Exception) {
                println("catch" + e)
            }
            val i = "s".toInt()
        }

        job.start()

        lifecycleScope.launch(handler + Dispatchers.IO) {

            delay(100)
            println("begin launch")
            try {
                ("aaa").toInt()
            } catch (_: Exception) {

            }

            println("withContext " +
                    //     try {
                    withContext(Dispatchers.IO) {
                        async {
                            ("111111").toInt()
                        }.await()
                    }
                /*         } catch (e: Exception) {
                             println("withContext $e")
                         }*/
            )

            println("coroutineScope " +
                    //   try {
                    coroutineScope {

                        async {
                            ("22222").toInt()
                        }.await()

                    }
                /*                   } catch (_: Exception) {
                                   }*/
            )


            delay(100)
            /*           println(
                           supervisorScope {
                               launch {
                                   ("3").toInt()
                               }
                           }

                       )*/

            println("end launch")


            val scope = coroutineScope{
                     val listAsync =listOf(1,2,3).map {
                    async {   it  }
                }
                 val result = listAsync.awaitAll()
            }

            val scope2 = supervisorScope {
                "scope"
            }

            scope2.plus(1)
        }

        lifecycleScope.launch()
        {
            var result = 0

            val mutex = Mutex()
            // val ms = measureTimeMillis {
            for (i in 1..10000) {
                val job = lifecycleScope.async(Dispatchers.Default) {


                    mutex.withLock {
                        delay(100)
                        return@async result++
                    }
                }

                println(job.await())
            }



            synchronized(this) {

            }




            /*     suspend fun getForecast() : String{
                     delay(1000)
                     return  "Sunny"
                 }

                 suspend fun getTemperature() : String{
                     delay(1000)
                     throw AssertionError("Temp is invalid")
                     return  "30 graduses"
                 }

                 suspend fun getWeatherReport() = coroutineScope{
                     val forecast = async {  getForecast() }
                     val temperature = async {  getTemperature() }
                     delay(200)
                     forecast.await()
                 }

                 runBlocking {
                     println(getWeatherReport())

                 }*/


        }

        suspend fun superJobTest() = coroutineScope{
            val jobS = launch(SupervisorJob()){
                launch {
                    delay(1000)
                    throw RuntimeException()
                }
                launch {
                    delay(1000)
                    println("will not be printed")
                }
            }

            jobS.join()
        }
    }

}