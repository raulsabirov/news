package com.example.news.DOCS

import androidx.compose.ui.Modifier


//https://proandroiddev.com/understanding-generics-and-variance-in-kotlin-714c14564c47
class Generics {

    var arrayListAny = ArrayList<Any>()
    var arrayListString = ArrayList<String>()
    var arrayListCharSequence = ArrayList<CharSequence>()

    val listStar: List<*> = listOf(42, "Bob")
    var listAny = listOf<Any>(1, "1")
    var listCharSequence = listOf<CharSequence>("1")
    var listString = listOf<String>("1")

    init {
        val orange: Orange

        // orange == Orange()

        // List is  `out` generic
        listAny = listString
        listCharSequence = listString


        // mapTo use  `in` generic
        arrayListAny = arrayListCharSequence.mapTo(arrayListAny) {
            it
        }


        arrayListString = arrayListCharSequence.mapTo(arrayListString) {
            it.toString()
        }

    }


    public fun starGeneric(listStars: ArrayList<*>) {
        listStars.add(TODO())
    }

    public inline fun <LIST_FROM, R, LIST_TO : MutableCollection<in R>> Iterable<LIST_FROM>.mapTo(
        destination: LIST_TO,
        transform: (LIST_FROM) -> R
    ): LIST_TO {
        for (item in this)
            destination.add(transform(item))
        return destination
    }
}


open class Fruit(var weight: Int = 0)

open class Citrus(color: Int = 0) : Fruit(2)

open class Orange() : Citrus() {
    init {
        weight = 3
    }
}

open class BigRoundOrange() : Orange()