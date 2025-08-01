package com.example.lib

open class InlineTest {
}
class Container<T : Any>  // T не может быть nullable
class NullableContainer<T>  // T может быть nullable


fun main() {

    println(ValueClass(AnaliticA()))
}



/*
inline fun <T> my(value : T ) where T : ValueClass {


    return value
}
*/


interface Analitics {

    val name: String

}


class AnaliticA : Analitics {
    override val name = "A"

}

class AnaliticB : Analitics {
    override val name = "B"
}

class AnaliticC : Analitics {

    override val name = "C"
}