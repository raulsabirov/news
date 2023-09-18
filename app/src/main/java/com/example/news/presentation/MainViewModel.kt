package com.example.news.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.GlobalState
import com.example.news.data.ArticlesRepository
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

sealed class LoadingState {
    object Default : LoadingState()
    object Start : LoadingState()
    data class Stop(val errorMsg: String? = null) : LoadingState()
}


class MainViewModel(private val articlesRepository: ArticlesRepository) :
    ViewModel() , KoinComponent {

    val router         : Router by inject()
    val navigatorHolder: NavigatorHolder by inject()

    var currentStateLiveDate =
        MutableLiveData<GlobalState>().apply { value = GlobalState.REGISTRATION }

    var loadingStateLiveDate =
        MutableLiveData<LoadingState>().apply { value = LoadingState.Default }


     suspend fun articlesFlow() = articlesRepository.getArticles()

    val sharedFlow = MutableSharedFlow<Int>(replay = 4)

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    init {

    }


    private suspend fun test1(){
        viewModelScope.launch(Dispatchers.IO){

        }

        println("123")


    }

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        loadingStateLiveDate.postValue(LoadingState.Stop(exception.toString()))
    }
}