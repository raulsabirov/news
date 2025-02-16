package com.example.news.patterns


// The Builder design pattern is used for constructing complex objects by separating the construction process
// from the actual representation. It is particularly useful when an object has a large number of parameters,
// and we want to provide a more readable and flexible way to construct it.
//


// Product class
data class Computer(
    val cpu: String,
    val ram: String,
    val storage: String,
    val graphicsCard: String
)

// Concrete builder class
class ComputerBuilder {
    private var cpu: String = ""
    private var ram: String = ""
    private var storage: String = ""
    private var graphicsCard: String = ""

    fun cpu(cpu: String): ComputerBuilder {
        this.cpu = cpu
        return this
    }

    fun ram(ram: String): ComputerBuilder {
        this.ram = ram
        return this
    }

    fun storage(storage: String): ComputerBuilder {
        this.storage = storage
        return this
    }

    fun graphicsCard(graphicsCard: String): ComputerBuilder {
        this.graphicsCard = graphicsCard
        return this
    }

    fun build(): Computer {
        return Computer(cpu, ram, storage, graphicsCard)
    }
}

fun main() {
    // Build the computer with a specific configuration
    val builder = ComputerBuilder()
    val gamingComputer = builder
        .cpu("Intel Core i9")
        .ram("32GB DDR4")
        .storage("1TB SSD")
        .graphicsCard("NVIDIA RTX 3080")
        .build()
}