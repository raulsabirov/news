package com.example.lib.algo

class Alfa_HashSet {
}

data class Dog(val breed: String = "shepherd") {
    var name = ""
}

// Сколько будет записей в Set? Почему?
// Как работает HashSet?
// Как работает HashMap?
val set = hashSetOf(
    Dog().apply { name = "Joe" },
    Dog("sharik"),
    Dog().apply { name = "Billy" }
)

fun main()  {

    println(set)
}