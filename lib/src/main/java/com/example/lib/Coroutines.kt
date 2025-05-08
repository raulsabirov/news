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
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.coroutines.resumeWithException

val coroutineScope = CoroutineScope(Job() + Dispatchers.IO + NonCancellable)


fun main() {

    val map = mutableMapOf(1 to 1, 1 to 2)
    println(map)

    var i =0
     runBlocking {
        var value = 0
        val jobs = (1..10_000).map {
            launch {
                withContext(Dispatchers.Default) {
                    value++
                }
                value++
            }
        }
        jobs.forEach {
            it.join()
        }
        check(value == 20_000)
    }

    runBlocking<Unit> {
        superJobTest2()
    }
}

fun test(  cl: () ->Int){
    println(cl())
    println("free  " + Runtime.getRuntime().freeMemory())
}


suspend fun superJobTest2() = coroutineScope{
      //  val s  = ()
    val supervisor = SupervisorJob()
    with(CoroutineScope(Job() + Dispatchers.IO)){
        val jobS = launch(supervisor){
            launch {
                this
                delay(1000)
                // throw RuntimeException()
                println("job 1")
            }
            launch {
                delay(1000)
                println("job 2")
            }
        }
    }
    delay(2000)
    supervisor.cancel()
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
                "this"
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


            suspendCancellableCoroutine {cont->
              //  cont.resume( 1)
              // cont.resumeWithException( Throwable())

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
    }


}