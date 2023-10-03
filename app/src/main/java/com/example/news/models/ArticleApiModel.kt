package com.example.news.models

data class ArticleApiModel(
    val status: String,
    val totalResults: Int,
    val articles: List<Article> = listOf()
)

/*

{
    "status": "ok",
    "totalResults": 61,
    "articles": [
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
    },
    {
        "source": {
        "id": "google-news",
        "name": "Google News"
    },
        "author": "Новости Новосибирска – ngs.ru",
        "title": "Астрофотограф снял в небе недалеко от Новосибирска Уран и Юпитер - 2 октября 2023 - Новости Новосибирска – ngs.ru",
        "description": null,
        "url": "https://news.google.com/rss/articles/CBMiMGh0dHBzOi8vbmdzLnJ1L3RleHQvc2NpZW5jZS8yMDIzLzEwLzAyLzcyNzY2MDQzL9IBAA?oc=5",
        "urlToImage": null,
        "publishedAt": "2023-10-02T09:01:22Z",
        "content": null
    },
    {
        "source": {
        "id": "google-news",
        "name": "Google News"
    },
        "author": "iXBT.com - новости техники и технологий",
        "title": "«Хаббл» показал краски осени в космосе на снимке туманности, сияющей оранжевым от молодых горячих звёзд - iXBT.com - новости техники и технологий",
        "description": null,
        "url": "https://news.google.com/rss/articles/CBMilQFodHRwczovL3d3dy5peGJ0LmNvbS9uZXdzLzIwMjMvMTAvMDIvaGFiYmwtcG9rYXphbC1rcmFza2ktb3Nlbmktdi1rb3Ntb3NlLW5hLXNuaW1rZS10dW1hbm5vc3RpLXNpamFqdXNoZWotb3JhbnpoZXZ5bS1vdC1tb2xvZHloLWdvcmphY2hpaC16dmpvemQuaHRtbNIBmQFodHRwczovL3d3dy5peGJ0LmNvbS9uZXdzLzIwMjMvMTAvMDIvaGFiYmwtcG9rYXphbC1rcmFza2ktb3Nlbmktdi1rb3Ntb3NlLW5hLXNuaW1rZS10dW1hbm5vc3RpLXNpamFqdXNoZWotb3JhbnpoZXZ5bS1vdC1tb2xvZHloLWdvcmphY2hpaC16dmpvemQuYW1wLmh0bWw?oc=5",
        "urlToImage": null,
        "publishedAt": "2023-10-02T08:01:00Z",
        "content": null
    },
    {
        "source": {
        "id": "google-news",
        "name": "Google News"
    },
        "author": "Forbes Russia",
        "title": "Как менялся взгляд на женскую психотерапию и чем она отличается от мужской - Forbes Russia",
        "description": null,
        "url": "https://news.google.com/rss/articles/CBMidGh0dHBzOi8vd3d3LmZvcmJlcy5ydS9mb3JiZXMtd29tYW4vNDk3NTAxLWthay1tZW5hbHNhLXZ6Z2xhZC1uYS16ZW5za3V1LXBzaWhvdGVyYXBpdS1pLWNlbS1vbmEtb3RsaWNhZXRzYS1vdC1tdXpza29q0gEA?oc=5",
        "urlToImage": null,
        "publishedAt": "2023-10-02T07:00:00Z",
        "content": null
    },
    {
        "source": {
        "id": "google-news",
        "name": "Google News"
    },
        "author": "NEWSru.co.il",
        "title": "Начался отбор в научные олимпийские сборные Израиля - NEWSru.co.il",
        "description": null,
        "url": "https://news.google.com/rss/articles/CBMiMGh0dHBzOi8vd3d3Lm5ld3NydS5jby5pbC9pc3JhZWwvMm9jdDIwMjMvbnMuaHRtbNIBAA?oc=5",
        "urlToImage": null,
        "publishedAt": "2023-10-02T06:10:50Z",
        "content": null
    }
    ]
}*/
