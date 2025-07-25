package com.example.lib.algo

fun main2() {
    val b = B()
    println(b.value) // 100
}

abstract class A {
    init {
        println(value) // 0
    }

    abstract val value: Int
}

class B : A() {
    override val value = 100
}


fun main() { // Iterator
    val values = mutableListOf(1, 2, 3)
    values.forEach {
        // do something useful
        values.remove(it)
    }
    println("$values")
}