package com.example.news.data

import com.example.news.BuildConfig
import com.example.news.models.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
) : NetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(provideOkHttpClient())
        .build().create(API::class.java)


// https://newsapi.org/v2/top-headlines?category=science&country=ru&apiKey=9e39934e997343cf8a4b6010d533a801&q=

    private fun interceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val url = request.url.newBuilder()
//            .addQueryParameter("category", "science")
                .addQueryParameter("country", "ru")
                .addQueryParameter("apiKey", "9e39934e997343cf8a4b6010d533a801")
                .build();

            request
                .newBuilder()
                .addHeader("X-Requested-With", "XMLHttpRequest")

                .url(url)
                .build()

            chain.proceed(request)
        }
    }

    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .writeTimeout(
                30,
                TimeUnit.SECONDS
            )
            .readTimeout(
                30,
                TimeUnit.SECONDS
            )
            .connectTimeout(
                30,
                TimeUnit.SECONDS
            )

        builder.addInterceptor(
            interceptor()
        )


        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addNetworkInterceptor(httpLoggingInterceptor)
        }


        return builder.build()
    }

    override fun getArticles(page: Int) = flow {

        try {
            val response = networkApi.query(page = page)
            if (response.isSuccessful)
                emit(response.body()?.articles ?: emptyList())

        } catch (e: Exception) {
            println(e)
            throw RetryException()
        }
       // emit(emptyList())

    }.retry(5) {
        if (it is RetryException) return@retry true


        return@retry false
    }


    class RetryException : Exception()

    override suspend fun getArticles2(page: Int) =
        networkApi.query(page = page).body()?.articles ?: emptyList()


}
