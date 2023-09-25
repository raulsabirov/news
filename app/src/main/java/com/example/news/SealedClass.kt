package com.example.news

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.random.Random

sealed class SealedClass {
    data class Success(val one: Int) : SealedClass()
    data class Error(val one: Int) : SealedClass()


    companion object : KoinComponent {
        const val con = "companion"
    }
}