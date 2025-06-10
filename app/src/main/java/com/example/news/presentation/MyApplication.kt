package com.example.news.presentation

import android.app.Application
import news.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//import com.example.news.data.DaggerAppComponent


class MyApplication : Application() {
   // lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
               viewmodelModule
            )
        }
       // appComponent = DaggerAppComponent.create()
        // Initialize Sync; the system responsible for keeping data in the app up to date.
//        Sync.initialize(context = this)
    }
    /*
        */
    /**
     * Since we're displaying SVGs in the app, Coil needs an ImageLoader which supports this
     * format. During Coil's initialization it will call `applicationContext.newImageLoader()` to
     * obtain an ImageLoader.
     *
     * @see https://github.com/coil-kt/coil/blob/main/coil-singleton/src/main/java/coil/Coil.kt#L63
     *//*
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }*/
}
