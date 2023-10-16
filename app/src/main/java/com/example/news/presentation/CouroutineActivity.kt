package com.example.news.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.news.databinding.ActivityCouroutineBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CouroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCouroutineBinding

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCouroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val job = lifecycleScope.launch() {
            delay(100)
        }

        // job.join()

        lifecycleScope.launch(handler + Dispatchers.IO) {
/*            delay(2000)
            sharedFlow.emit(1)
            delay(2000)
            sharedFlow.emit(2)
            delay(2000)
            sharedFlow.emit(3)
            delay(2000)
            sharedFlow.emit(4)*/


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

            /*           println(
                           supervisorScope {
                               launch {
                                   ("3").toInt()
                               }
                           }

                       )*/

            println("end launch")


            /*       lifecycleScope.launch()
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

       }*/

            synchronized(this) {

            }
        }
    }


//  Class used to run a message loop for a thread. Threads by default do not have a message loop
//  associated with them; to create one, call prepare in the thread that is to run the loop,
//  and then loop to have it process messages until the loop is stopped.
//   Most interaction with a message loop is through the Handler class.

    // Preparing a Thread for HaMeR
    internal class LooperThread : Thread() {
        var mHandler: Handler? = null
        override fun run() {
            // adding and preparing the Looper
            Looper.prepare()
            // the Handler instance will be associated with Thread’s Looper
            mHandler = object : Handler(Looper.myLooper()!!) {
                override fun handleMessage(msg: Message) {
                    // process incoming messages here
                }
            }
            // Starting the message queue loop using the Looper
            Looper.loop()
        }
    }
}