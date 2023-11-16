package com.example.news.data

import com.example.news.ArticlesFragment
import com.example.news.presentation.MainActivity
import dagger.Component

@Component(modules = [FirstModule::class, SecondModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ArticlesFragment)
}