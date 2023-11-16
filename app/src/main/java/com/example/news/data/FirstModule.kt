package com.example.news.data

import dagger.Binds
import dagger.Module

@Module
abstract class FirstModule {
    @Binds
    abstract fun provideNetworkDataSource(retrofitDataSource: RetrofitDataSource): NetworkDataSource

    @Binds
    abstract fun provideLocalDataSource(retrofitDataSource: CacheDataSource): LocalDataSource
}


@Module
abstract class SecondModule {

    @Binds
    abstract fun provideRepository(articlesRepositoryImpl: ArticlesRepositoryImpl): ArticlesRepository

}
