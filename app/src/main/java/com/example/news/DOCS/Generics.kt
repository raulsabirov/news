package com.example.news.DOCS


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
        // arrayListAny = arrayListString


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

    public inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapTo(destination: C, transform: (T) -> R): C {
        for (item in this)
            destination.add(transform(item))
        return destination
    }
}

