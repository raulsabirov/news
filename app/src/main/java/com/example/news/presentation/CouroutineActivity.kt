package com.example.news.presentation

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.news.R
import com.example.news.databinding.ActivityCouroutineBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class CouroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCouroutineBinding

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCouroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)




        lifecycleScope.launch(handler + Dispatchers.IO) {
/*            delay(2000)
            sharedFlow.emit(1)
            delay(2000)
            sharedFlow.emit(2)
            delay(2000)
            sharedFlow.emit(3)
            delay(2000)
            sharedFlow.emit(4)*/


            coroutineScope {
                ("a").toInt()
            }


            println("end launch")


        }




    }


}