package com.example.lib

abstract class AbstractClass {
    open val valueS : String = " "

    fun fun1 (value : String){

    }

   abstract fun fun2 (value : String)

    abstract  fun fun3 (value : String)
}

interface Interface {
    val valueS : String

    fun fun1 (value : String) {  println()
    }

    fun fun2 (value : String)
    fun fun3 (value : String)



    companion object{
        val value2 : String = " "
    }
}


class MyClass() :AbstractClass(){

    override val valueS : String = " "

    override fun fun2(value: String) {
        TODO("Not yet implemented")
    }

    override fun fun3(value: String) {
        TODO("Not yet implemented")
    }

}