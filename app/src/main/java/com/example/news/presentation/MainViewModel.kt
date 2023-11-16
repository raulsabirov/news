package com.example.news.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.FlowEvent
import com.example.news.Navigation
import com.example.news.data.ArticlesRepository
import com.example.news.data.ArticlesRepositoryImpl
import com.example.news.data.CacheDataSource
import com.example.news.data.LocalDataSource
import com.example.news.data.NetworkDataSource
import com.example.news.data.RetrofitDataSource
import com.example.news.models.Article

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _navigationFlow = FlowEvent<Navigation>()
    val navigationFlow: SharedFlow<Navigation> = _navigationFlow.asSharedFlow()

    val articlesFlow = MutableStateFlow<List<Article>>(emptyList())

    var loadingStateLiveDate =
        MutableLiveData<LoadingState>().apply { value = LoadingState.Default }


    val sharedFlow = MutableSharedFlow<Int>(replay = 4)

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }


    fun navigate(navigation: Navigation) {
        _navigationFlow.tryEmit(navigation)
    }


    fun getArticles(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            articlesFlow.emitAll(articlesRepository.getArticles(page = page))

            //   articlesRepository.getArticles2(page=page)
        }
    }

    private suspend fun test1() {
        viewModelScope.launch(Dispatchers.IO) {

        }

        println("123")


    }

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        loadingStateLiveDate.postValue(LoadingState.Stop(exception.toString()))
    }
}