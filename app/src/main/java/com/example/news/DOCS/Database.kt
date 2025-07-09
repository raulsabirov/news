package com.example.news.DOCS

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

class Database private constructor(val context: Context) {

    companion object {

        lateinit var instance1: Database

        @Volatile
        private var  instance: Database? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): Database {


            if (! this::instance1.isInitialized) {
                synchronized(Database::class.java) {

                    instance1 = Database(context)
                    return instance1
                }
            }
            return instance1



            return instance ?: synchronized(this){
                instance ?: Database(context)
            }
        }

    }
}