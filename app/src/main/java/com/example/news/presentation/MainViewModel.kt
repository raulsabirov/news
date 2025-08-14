package com.example.news.presentation

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.news.FlowEvent
import com.example.news.R
import com.example.news.models.Article
import com.example.news.presentation.fragments.Navigation

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore
import kotlin.properties.Delegates.notNull

/*sealed class LoadingState() {
    object Default : LoadingState()
    object Start : LoadingState()
    data class Stop(val errorMsg: String? = null) : LoadingState()
}*/


class MainViewModel  constructor(
  //  val articlesRepository: ArticlesRepositoryImpl
) :
    ViewModel() {
    private val list = List(10) { counter ->
        Article(
            title = "Article ${counter}",
            description = "Description for article ${counter}"
        )
    }


    val stateArticleList  = list.toMutableStateList()

    override fun onCleared() {
        list.asReversed()
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

    val stateResponse = MutableStateFlow("").shareIn(viewModelScope, SharingStarted.Eagerly)

    val stateRequest = MutableStateFlow("")

    var laz = lazy { 1 }
    lateinit var  latinit : Article
    val v = listOf(1, 1 * 2)


    val lsit = listOf(1, 2, 3, "")

    init {
        mutableNumberList.forEach{


            return@forEach
        }
     //   getArticles()
        val s = Semaphore(2)
        laz = lazy { 2 }
        //     mutableNumberList = mutableIntList
        //      DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault())

        val ll = (intList as MutableList)

        val job = viewModelScope.launch(Dispatchers.IO) {

            stateRequest.value = "1"

            stateRequest
                .collect {


                }

            stateRequest.update { "" }

        }
        job.ensureActive()
        runBlocking {

        }
    }


    val thred = Thread{

    }.run {  }

    // val flow = Flow(viewModelScope)

    private val _navigationFlow = FlowEvent<Navigation>()
    val navigationFlow: SharedFlow<Navigation> = _navigationFlow.asSharedFlow()

    val _articlesFlow = MutableStateFlow<List<Article>>(emptyList())

    val articlesFlow = MutableStateFlow<List<Article>>(emptyList())


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
    var volitileList = listOf(1, 2, 3)


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

fun guide() {
    print("guide start")
    teach {
        print("teach")
        return@teach
    }
    print("guide end")

    val intArray: IntArray
}

inline fun teach(abc: () -> Unit) {
    abc()
}


class Student(val name: String) {
    init {

    }

    constructor(sectionName: String, id: String) : this(sectionName) {
        /*      init{

              }*/
    }
}


data class Student2(val firstName: String, val secondName: String) {

}

fun myFun() {
    lateinit var r: String

    val student = Student2("1", "2")

    val newSecondName = "3"

    val applyStudent = student.apply { Student2("1", newSecondName) }

    val letStudent = student.let { Student2(it.firstName, newSecondName) }

}



