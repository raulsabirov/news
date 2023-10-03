package com.example.news.DOCS


//https://proandroiddev.com/understanding-generics-and-variance-in-kotlin-714c14564c47
class Gererics {


    var arrayListAny = ArrayList<Any>()
    var arrayListString = ArrayList<String>()

    var listAny = listOf<Any>(1)
    var listCharSequence = listOf<CharSequence>("1")
    var listString = listOf<String>("1")


    init {
        // arrayListAny = arrayListString


        listAny = listString
        listCharSequence = listString


    }
}

val dogCompare: Compare<Dog> = object : Compare<Dog> {
    override fun compare(first: Dog, second: Dog): Int {
        return first.cuteness - second.cuteness
    }
}