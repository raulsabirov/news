package com.example.news.patterns


/*
The decorator design pattern allows behavior to be added to an individual object,
either statically or dynamically without affecting the behavior of other objects from the same class.
In Kotlin, we can implement the decorator pattern using interfaces and classes.
*/

/*
In this example, the decorate extension function is added to the Car interface. This extension function takes a lambda parameter called initialize,
 which represents the additional behavior to be added. It returns a new instance of Car that incorporates the specified behavior before calling the original drive method.

In the main function, the basic car is decorated using the decorate extension function to create an offroad car, and then the offroad car is driven.

The output for this code example will be:

Configure offroad driving mode
Move from A to

*/

// Component interface
interface Car {
    fun drive()
}

// Concrete component
class BasicCar : Car {
    override fun drive() {
        println("Move from A to B")
    }
}

// Extension function for Car interface
fun Car.decorate(initialize: () -> Unit): Car {
    return object : Car {
        override fun drive() {
            initialize()
            this@decorate.drive()
        }
    }
}

fun main() {
    // Create a basic car
    val myBasicCar: Car = BasicCar()

    // Decorate it to make it an offroad car
    val offroadCar: Car = myBasicCar.decorate {
        println("Configure offroad driving mode")
    }

    // Drive the offroad car
    offroadCar.drive()
}