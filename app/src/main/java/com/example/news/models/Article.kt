package com.example.news.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
//import kotlinx.parcelize.Parcelize
import java.util.UUID


@JvmInline
value class Email(val value: String)
{
    constructor(): this("")

    init {
      //  require{  value > 0}
    }

  fun myFun(): Int{
      return  1
  }
}

data class AA( val s: String){
    constructor(

    ) : this("")


}
/*
@Immutable
data class Article(
    val id: String =  UUID.randomUUID().toString(),
    val source: Source? = Source(),
    val author: String? = null,
    val title: String,
    val description: String,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    @Transient
    val  content: String? = null
)

@Immutable
data class Source (
    val id: String? = null,
    val name: String? = null
) : A() {
*/
/*    override val a: Nothing
        get() = super.a*//*

}

 abstract class A ( val aa :String = "String"){
     val a = ""

     val list : List<String> = emptyList()
 //   @Parcelize
    companion object  {

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }
    }
}
*/


/*
{
    "source": {
    "id": "google-news",
    "name": "Google News"
            },
    "author": "Интерфакс",
    "title": "Объявлены лауреаты Нобелевской премии по медицине и физиологии - Интерфакс",
    "description": null,
    "url": "https://news.google.com/rss/articles/CBMiJGh0dHBzOi8vd3d3LmludGVyZmF4LnJ1L3dvcmxkLzkyMzY3OdIBAA?oc=5",
    "urlToImage": null,
    "publishedAt": "2023-10-02T09:47:00Z",
    "content": null
}*/
