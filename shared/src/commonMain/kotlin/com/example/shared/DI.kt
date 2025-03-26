package com.azharkova.kmm_news

import com.azharkova.kmm_news.network.NetworkClient
import io.ktor.client.HttpClient
import org.koin.dsl.module
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DI {
    val networkClient: NetworkClient by lazy {
        NetworkClient()
    }

    val newsService: NewsService by lazy {
        NewsService(networkClient)
    }
}


private val coreModules = get() = listOf(


)


val networkModule = module{
    single<HttpClient> {cre}
}