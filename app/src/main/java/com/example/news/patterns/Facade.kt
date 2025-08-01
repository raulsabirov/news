package com.example.news.patterns


/*
The Facade design pattern provides a simplified interface to a set of interfaces in a subsystem, making it easier to use. It involves creating a class that represents a higher-level, unified interface that makes it easier for clients to interact with a subsystem. This can help simplify the usage of complex systems by providing a single entry point.

Let’s create a simple example of the Facade pattern in Kotlin. Consider a subsystem with multiple classes that handle different aspects of a computer system, CPU, Memory, and Hard Drive.

We’ll create a ComputerFacade class to provide a simple interface for the client to interact with the subsystem:

*/

/*


In this example, the ComputerFacade class serves as a simplified interface for starting the computer system.
The client interacts with the subsystem (CPU, Memory, and HardDrive) through the ComputerFacade without needing to know the details of each subsystem component.

By using the Facade pattern, the complexity of the subsystem is hidden from the client,
and the client can interact with the system through a more straightforward and unified interface provided by the facade.
This can be especially useful when dealing with large and complex systems.
*/
/**
 * @see <img src="https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/Completable.png"/>
 */

// Subsystem classes
class CPU {
    fun processData() {
        println("Processing data...")
    }
}

class Memory {
    fun load() {
        println("Loading data into memory...")
    }
}

class HardDrive {
    fun readData() {
        println("Reading data from hard drive...")
    }
}

// Facade class
class ComputerFacade(
    private val cpu: CPU,
    private val memory: Memory,
    private val hardDrive: HardDrive
) {
    fun start() {
        println("ComputerFacade starting...")
        cpu.processData()
        memory.load()
        hardDrive.readData()
        println("ComputerFacade started successfully.")
    }
}

// Client code
fun main() {
    // Create subsystem components
    val cpu = CPU()
    val memory = Memory()
    val hardDrive = HardDrive()

    // Create facade and pass subsystem components to it
    val computerFacade = ComputerFacade(cpu, memory, hardDrive)

    // Client interacts with the subsystem through the facade
    computerFacade.start()
}