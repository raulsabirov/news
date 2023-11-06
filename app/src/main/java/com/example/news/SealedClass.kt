package com.example.news



sealed class SealedClass {
    data class Success(val one: Int) : SealedClass()
    data class Error(val one: Int) : SealedClass()


    companion object {
        const val con = "companion"
    }
}