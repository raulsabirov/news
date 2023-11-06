package com.example.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.ArticlesRepository
import com.example.news.presentation.LoadingState

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsDetailViewModel(private val articlesRepository: ArticlesRepository) :
    ViewModel() {


    var loadingStateLiveDate =
        MutableLiveData<LoadingState>().apply { value = LoadingState.Default }


    init {
        viewModelScope.launch(Dispatchers.IO) {

        }


    }

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        loadingStateLiveDate.postValue(LoadingState.Stop(exception.toString()))
    }
}