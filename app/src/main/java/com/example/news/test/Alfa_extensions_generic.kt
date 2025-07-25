package com.example.news.test

import android.app.Activity
import java.io.Serializable



/*
// Сделать эту функцию экстеншеном для активити и исправить ошибки
fun <T : Serializable?> getSerializable(name: String) {
    return intent.getSerializableExtra(name) as T
}
*/

inline fun < reified T : Serializable?> Activity.getSerializable(key : String) : T?{
    return  intent.getSerializableExtra(key) as? T

}

