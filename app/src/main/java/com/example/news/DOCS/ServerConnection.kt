package com.example.news.DOCS


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


interface  ServerConnection {

    fun event() : Flow<String>
}


class FirstComponent(val connect : ServerConnection, scope : CoroutineScope, val map : (String) -> Boolean){


    init {
     //  val a = return 1
        scope.launch {
            connect
                .event()
                .filter {
                    map(it)
                }
                .collect {

                }

        }

    }

    fun myFun () =
        connect
            .event()
            .filter {
                map(it)
            }

}

class SecondComponent(connect : ServerConnection ){




}