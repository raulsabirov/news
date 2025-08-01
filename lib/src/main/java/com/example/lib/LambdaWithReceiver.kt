package com.example.lib




fun main() {




}

interface Ireceiver {
    val s : String
    val i : Int
}


class ClassReceiver (
    val s : String,
    val i : Int
)


class LambdaWithReceiver {

    val classReceiver1 = ClassReceiver ("1",1)
    val classReceiver2 = ClassReceiver ("2",2)

    fun foo(classReceiver: ClassReceiver.() -> Unit) {

        classReceiver1.classReceiver()
    }



    fun foo2() {

        foo {
           println(s)
            println(i)
        }

        foo {
            classReceiver2
        }

    }

}
