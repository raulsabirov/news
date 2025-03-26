package com.example.news




val lazy by lazy{
    Generics().open1
}
// https://proandroiddev.com/understanding-generics-and-variance-in-kotlin-714c14564c47
// https://typealias.com/guides/ins-and-outs-of-generic-variance/

interface MyInterface {

  //  @Volatile
    var list : List<String>


 //   @Volatile
    val  defaultName: String
         get() = "Hello" // Значение не хранится, но всегда возвращается одно и то же
}

open class Generics {
    private   val   private1 = ""
    protected val   protected1 = ""
    open val   open1 = ""

    inner class  innerClas(){

        fun myFun(){
            open1
            private1
            protected1
        }
    }
    @Volatile
    var  defaultName: String? = null


    var arrayListAny = ArrayList<Any>()
    var arrayListString = ArrayList<String>()
    var arrayListCharSequence = ArrayList<CharSequence>()

   // val listStar: List<*> = listOf(42, "Bob")
    var listAny = listOf<Any>(1, "1")
    var listCharSequence = listOf<CharSequence>("1")
    var listString = listOf<String>("1")
    var mutableListString = mutableListOf<String>("1")

    var listOrange = listOf(Orange())
    var listCitrus = listOf(Citrus())
    var listFruit = listOf(Fruit())

    init {
        // Covariance
      //  val dogList: List<Dog> = listOf(Dog(10), Dog(20))
      //  val animalList: List<Animal> = dogList


        // Invariance
     //  val dogList2: List<Dog> = mutableListOf<Dog>(Dog())
     //  val animalList2: List<Animal> = dogList2 // Compiler error


        // Contravariance
        val dogCompare: Comparator<Dog> = object: Comparator<Dog> {
            override fun compare(first: Dog, second: Dog): Int {
                return first.cuteness - second.cuteness
            }
        }
   //      val animalCompare: Comparator<Animal> = dogCompare // Compiler error

        val animalCompare2: Comparator<Animal> = object: Comparator<Animal> {
            override fun compare(first: Animal, second: Animal): Int {
                return first.size - second.size
            }
        }
    //    val spiderCompare: Comparator<Spider> = animalCompare2 // Works nicely!

        val orange: Orange

        val numbers = listOf(1, 2, 3)
        val expanded = numbers.flatMap { listOf(it, it * 2) }
        println(expanded) // [1, 2, 2, 4, 3, 6]


        val list = listOf("123", "45")
        println(list.flatMap { it.toList() }) // [1, 2, 3, 4, 5]


        val colors = listOf("red", "brown", "grey")
        val animals = listOf("fox", "bear", "wolf")
        println(colors zip animals)

        val twoAnimals = listOf("fox", "bear")
        println(colors.zip(twoAnimals))
       // [(red, fox), (brown, bear), (grey, wolf)]
       // [(red, fox), (brown, bear)]

        val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
        println(numberSets.flatten())
        // [1, 2, 3, 4, 5, 6, 1, 2]

/*        val containers = listOf(
            StringContainer(listOf("one", "two", "three")),
            StringContainer(listOf("four", "five", "six")),
            StringContainer(listOf("seven", "eight"))
        )
        println(containers.flatMap { it.values })*/
        // [one, two, three, four, five, six, seven, eight]

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



    val refOut: OutClass<Any> = OutClass("string")
    val refIn: InClass<Int>   = InClass<Number>()


}


class OutClass<out T>(val value: T) {
    fun get(): T {
        return value
    }
}

class InClass<in T> {
    fun toString(value: T): String {
        return value.toString()
    }
}


// The out keyword says that methods in a List can only return type E
// and they cannot take any E types as an argument.
// This limitation allows us to make List covariant.
interface List<out E> {
    fun get(index: Int): E
}

// In this case, there is in keyword next to the parameter.
// This means that all methods in Compare can have T as an argument but cannot return T type.
// This makes Compare contravariant.
interface Compare<in T> {
    fun compare(first: T, second: T): Int
}



abstract class Animal(val size: Int)
class Dog(val cuteness: Int): Animal(100)
class Spider(val terrorFactor: Int): Animal(1)

open class Fruit(var weight: Int = 0)
open class Citrus(color: Int = 0) : Fruit(2)
open class Orange() : Citrus() {
    init { weight = 3  }
}

open class BigRoundOrange() : Orange()


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
