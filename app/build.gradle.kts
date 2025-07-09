
plugins {
//    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinAndroid)

    // kotlin("android")
      //   id(libs.plugins.kotlin.kapt.get().pluginId) // подключение kapt
   // id("org.jetbrains.kotlin.kapt") // ❗ подключаем как строку

}



/*

kotlin {
    androidTarget()

    *//*    listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "app"
                binaryOptions["bundleId"] = "ru.braveowlet.simple_mvi_example.app"
            }
        }*//*

    sourceSets {
*//*        commonMain.dependencies {
         //   implementation(projects.common.logger)
         //   implementation(projects.common.mvi.mviGeneral)
        //    implementation(projects.common.mvi.mviKoinVoyager)
            implementation(projects.core.network)
            implementation(projects.core.recources)
            implementation(projects.core.database)
            implementation(projects.components.dogs)
            implementation(projects.features.mainScreen.mainScreenApi)
            implementation(projects.features.mainScreen.mainScreenImpl)
            implementation(projects.features.dogScreens.dogScreensImpl)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.screenModel)
            implementation(libs.voyager.navigator)
        }*//*
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
            implementation(libs.androidx.material)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.koin.android)
            implementation(libs.koin.android.compose)
            implementation(libs.koin.android.navigation)
            implementation(libs.dagger)
           // kapt(libs.dagger.compiler)
        }
    }
}*/

android {

    namespace = "com.example.news"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.news"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    defaultConfig {

        multiDexEnabled  = true
    }
/*
    kapt {
        generateStubs = true
    }*/



    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "NEWS_API_KEY", "\"9e39934e997343cf8a4b6010d533a801\"")
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
        }

        getByName("debug") {
            buildConfigField("String", "NEWS_API_KEY", "\"9e39934e997343cf8a4b6010d533a801\"")
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
        }
    }

/*
    compileOptions {
        sourceCompatibility  = JavaVersion.VERSION_17
        targetCompFatibility  = JavaVersion.VERSION_17
    }
*/

    buildFeatures {

        compose  = true
        buildConfig = true
    }
/*    composeOptions {
        kotlinCompilerExtensionVersion '1.4.3'
    }*/
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


}
/*

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
 //   stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
}
*/


dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.androidx.material)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)
    implementation(libs.koin.android.navigation)
    implementation(libs.dagger)
    implementation(libs.core.ktx)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.work.runtime.ktx)

    implementation(projects.features.articlesScreens.articlesImpl)

    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)

    val work_version = "2.10.2"


    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$work_version")
}



