
plugins {
    /// alias(libs.plugins.news.android.application)
    // alias(libs.plugins.news.android.compose)
    id("news.android.application")
    id("news.android.compose")
      //     alias(libs.plugins.kotlin.parcelize)

    id("kotlin-parcelize")
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
    
    defaultConfig {
        applicationId = "com.example.news"
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
    }
}
/*

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
 //   stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
}
*/


dependencies {
    implementation(libs.androidx.material)
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
  //  implementation(projects.shared)

    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    
    // Decompose
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
    implementation(libs.essenty.lifecycle)
    
    // Benchmark dependencies
/*    androidTestImplementation("androidx.benchmark:benchmark-junit4:1.2.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")*/
}



