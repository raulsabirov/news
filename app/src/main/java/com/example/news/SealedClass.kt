package com.example.news



sealed class SealedClass( ) {
    data class Success(val one: Int) : SealedClass()
    data class Error(val one: Int) : SealedClass()


    companion object {
        const val con = "companion"
    }
}

sealed class SealedClass2:SealedClass() {
    data class Success(val one: Int) : SealedClass2()
    data class Error(val one: Int) : SealedClass2()


    companion object {
        const val con = "companion"
    }
}

//var SomeClass.b = 1

class SomeClass():SomeInterface{
    public override val number : Int =1
        ///val l : SealedClass = SealedClass()

}

 interface SomeInterface{

    public val number : Int


   fun myFun() : Unit {
        println()
    }
}