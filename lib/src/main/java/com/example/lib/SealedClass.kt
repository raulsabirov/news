package com.example.lib


import java.util.LinkedList
import kotlin.collections.List


 enum class MYENUM{
    A,
    B,
    C
}

fun testEnum () {
    val enu = MYENUM.A


    val result = when(enu){
        MYENUM.A -> 1
        MYENUM.B -> 2
        MYENUM.C -> 3

    }

}

val String.firstChar
    get() = this[0]

var StringBuilder.lastChar: Char
    get() = this[length - 1]
    set(value) {
        this.setCharAt(length - 1, value)
    }


class ATest(val s: String, i: Int)

fun main() {
    val list1: List<Int> = mutableListOf(1, 2, 3)
    val list2: List<Int> = mutableListOf(1, 2, 3)

    println(list1.equals(list2))
    println(list1 === list2)

    val a1 = ATest("1", 1)
    val a2 = ATest("1", 1)


    println(a1.equals(a2))
    println(a1 === a2)


}

/*
class ResourceWrapper(private val context: Context) {
    fun getString(resId: Int): String {
        return context.getString(resId)
    }
}
*/

interface MyInt {
    //     @Volatile
    public var number: Int
    var laz: Lazy<Int>
        get() = lazy { 1 }
        set(value) = TODO()
}

sealed class SealedClass(val one1: Int) {
    data class Success(val one: Int) : SealedClass(one1 = one) {

    }

    data class Error(val one: Int) : SealedClass(one1 = one)


    companion object {
        const val con = "companion"
    }

}

data class Error1(val one: Int) : SealedClass(one1 = one)

sealed class SealedClass2 {
    data class Success(val one: Int) : SealedClass2()
    data class Error(val one: Int) : SealedClass2()


    companion object {

        const val con = "companion"

        val sClass = SealedClass.Success(1)

            // val sClass2 = SealedClass.Error1(1)

        @Synchronized
        fun myFun2(): Unit {
            println()

        }
    }
}

//var SomeClass.b = 1


fun main2() {

    SomeClass().test

    SomeClass.Name
}

class SomeClass() {
    val name: String

    init {
        printName()

        name = "Murzik"
    }

    val test = {
        println("test lambda")

    }

    fun test() = { println("test function") }

    private fun printName() {
        println(name)
    }

    object Name {

    }
}

///val l : SealedClass = SealedClass()

val map = mutableMapOf(1 to 1, 1 to 2)


@Synchronized
fun myFun2(): Unit {
    println()

    /*        myFunSealedClass().apply { it ->

            }*/
}

fun myFunSealedClass(): SealedClass {
    return SealedClass.Success(1)
}


val someInterface = object : SomeInterface {
    override var number: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override val linkedList: LinkedList<Int>
        get() = TODO("Not yet implemented")

    override fun myFun() {
        map
        this.number
    }
}

val somelambda: () -> String = {
    map

    ""
}





interface SomeInterface {

    //     @Volatile
    public var number: Int

    public val linkedList: LinkedList<Int>

    //   @Synchronized
    fun myFun(): Unit {
        println()
    }

    companion object {

        ///Marks the JVM backing field of the annotated var property as volatile,
        // meaning that reads and writes to this field are atomic and writes are always made visible to other threads.

        // @Volatile
        val list: List<Int> = mutableListOf(1, 2, 3)

        @Volatile
        var list2: List<Int> = mutableListOf(1, 2, 3)

        @Volatile
        var list3: List<Int> = listOf(1, 2, 3)


        fun synchronizedExample() {
            val inter = SomeInterface.Companion::class.java
            kotlin.synchronized(inter) {

            }
        }
    }
}


public class CoroutineTest2 {

    fun main() {

        kotlin.synchronized(this) {

        }

        CoroutineTest2.main { }
    }

    fun synchronizedExample() {
        kotlin.synchronized(this) {
            SomePrivateClass
        }
    }

    private companion object {
        fun main(str: SomePrivateClass.() -> Unit) {
        }
    }
}

class SomePrivateClass private constructor(
    val param1: String,
    val param2: String,
) {

    val users: List<String>
        get() = listOf("")

    companion object {
        @Volatile
        private var instance: SomePrivateClass? = null

        fun synchronizedExample() {
            kotlin.synchronized(this) {

            }
        }


        fun main(str: SomePrivateClass.() -> Unit) {
        }
    }
}