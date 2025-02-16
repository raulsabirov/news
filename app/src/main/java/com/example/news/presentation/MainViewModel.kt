package com.example.news.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.Coroutines
import com.example.news.Flow
import com.example.news.FlowEvent
import com.example.news.R
import com.example.news.data.ArticlesRepository
import com.example.news.models.Article

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

sealed class LoadingState {
    object Default : LoadingState()
    object Start : LoadingState()
    data class Stop(val errorMsg: String? = null) : LoadingState()
}


class MainViewModel @Inject constructor(
    val articlesRepository: ArticlesRepository
) :
    ViewModel() {

    fun myFun() = articlesRepository.getArticles(1)

    val coroutine = Coroutines(viewModelScope)

    var mutableIntList = mutableListOf<Int>(1)
    var mutableNumberList = mutableListOf<Number>(1)

    var intList : List<Int> = mutableListOf<Int>(1)

    val stateResponse = MutableStateFlow("")

    val stateRequest = MutableStateFlow("")

    val laz = lazy { 1 }

    init {
   //     mutableNumberList = mutableIntList
  //      DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault())

       val ll =  (intList as MutableList)

        val job =  viewModelScope.launch(Dispatchers.IO) {

            stateRequest.value = "1"

            stateRequest
                .collect()

        }

        runBlocking {

        }
    }


   // val flow = Flow(viewModelScope)

    private val _navigationFlow = FlowEvent<Navigation>()
    val navigationFlow: SharedFlow<Navigation> = _navigationFlow.asSharedFlow()

    val _articlesFlow = MutableStateFlow<List<Article>>(emptyList())

    val articlesFlow = MutableStateFlow<List<Article>>(emptyList())
    val v = listOf(1, 1 * 2)

    val lsit  = listOf(1,2,3,"")

  //  var loadingStateLiveDate =
   //     MutableLiveData<LoadingState>().apply { value = LoadingState.Default }


    val sharedFlow = MutableSharedFlow<Int>(replay = 4)

    val stateFlow = MutableStateFlow<Int?>(null)

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    val idResource = R.string.app_name

  //  val res = Result(2)
    fun navigate(navigation: Navigation) {
        _navigationFlow.tryEmit(navigation)
    }


    @Volatile
    var volitileList = listOf(1,2,3)

    fun getArticles(page: Int = 1) {
      val job =  viewModelScope.launch(Dispatchers.IO) {
          val articles =articlesRepository.getArticles(page = page)


        //  articlesFlow.value  = articles
          articlesFlow. emitAll(articles)
        //  articlesFlow. emit(articles)

          articlesFlow
              .onEach {  }
              .collect{ }
            //   articlesRepository.getArticles2(page=page)

          idResource.plus(1)
        }


        articlesFlow.apply {  }
        job.cancel()

    }

    private suspend fun test1() {

        runBlocking {

        }
            stateFlow.emit(1)

            stateFlow.emit(2)


            stateFlow
                .onEach {   }
                .collect{
                        value ->
                    delay(1)
                    println("Collected $value")
                }



        println("123")

        delay(1)
    }

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
       // loadingStateLiveDate.postValue(LoadingState.Stop(exception.toString()))
    }
}