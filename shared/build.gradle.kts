@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
   id(libs.plugins.news.android.library.get().pluginId)
   id(libs.plugins.news.kotlin.multiplatform.get().pluginId)

   // alias(libs.plugins.ksp)
}


android {
    namespace = "com.example.myapplication.shared"
}

