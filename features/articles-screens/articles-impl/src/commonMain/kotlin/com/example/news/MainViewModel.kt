package com.example.news

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.Article

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore
import kotlin.properties.Delegates.notNull

sealed class LoadingState() {
    object Default : LoadingState()
    object Start : LoadingState()
    data class Stop(val errorMsg: String? = null) : LoadingState()
}


class MainViewModel  constructor(
  //  val articlesRepository: ArticlesRepositoryImpl
) :
    ViewModel() {
/*    private val list = List(10) { counter ->
        Article(
            title = "Article ${counter}",
            description = "Description for article ${counter}"
        )
    }

    val stateArticleList  = list.toMutableStateList()*/

    override fun onCleared() {
        viewModelScope.launch {

            super.onCleared()
        }
    }

    val nul: String by notNull()
  //  fun myFun() = articlesRepository.getArticles(1)

    //val coroutine = Coroutines(viewModelScope)

    var mutableIntList = mutableListOf<Int>(1)
    var mutableNumberList = mutableListOf<Number>(1)

    var intList: List<Int> = mutableListOf<Int>(1)

    val stateResponse = MutableStateFlow("")

    val stateRequest = MutableStateFlow(1)

    val stateInt = MutableStateFlow(1)

    var laz = lazy { 1 }
    //lateinit var  latinit : Int

    init {
     //   getArticles()
        val s = Semaphore(2)
        laz = lazy { 2 }
        //     mutableNumberList = mutableIntList
        //      DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault())

        val ll = (intList as MutableList)

        val job = viewModelScope.launch(Dispatchers.IO) {

            repeat(1000){

                stateInt.update { it }
                delay(1000)
            }
        }

    }





    val _articlesFlow = MutableStateFlow<List<Article>>(emptyList())

    val articlesFlow = MutableStateFlow<List<Article>>(emptyList())


/*
    fun removeArticle(article : Article) {
        stateArticleList.remove(article)
    }

    fun addArticle() =
        stateArticleList.add(
            Article(
                title = "Article ${stateArticleList.size -1}",
                description = "Description for article ${stateArticleList.size -1}"
            )
        )
*/

/*
    fun getArticles(page: Int = 1) {
        val job = viewModelScope.launch(Dispatchers.IO) {
            val articles = articlesRepository.getArticles(page = page)


            //  articlesFlow.value  = articles
            articlesFlow.emitAll(articles)
            //  articlesFlow. emit(articles)

            articlesFlow
                .onEach { }
                .collect { }
            //   articlesRepository.getArticles2(page=page)

            idResource.plus(1)
        }


        articlesFlow.apply { }
        job.cancel()

    }
*/


    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        // loadingStateLiveDate.postValue(LoadingState.Stop(exception.toString()))
    }

}



