plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

compose.resources {
    generateResClass = never
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain.dependencies {

            implementation(projects.core.recources)
            implementation(projects.core.network)


            implementation(libs.kotlinx.coroutines.core)
          //  implementation(libs.kotlinx.coroutines.android)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)


            implementation(libs.coil.compose)
            implementation(libs.coil.network)

            implementation(libs.androidx.lifecycle.viewmodel.ktx)
          //  implementation(libs.androidx.lifecycle.viewmodel)
        }

/*        androidMain.dependencies {
*//*            implementation(projects.core.recources)
            implementation(projects.core.network)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.coroutines.android)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.compose.viewmodel)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)

            implementation(libs.voyager.koin)
            implementation(libs.voyager.screenModel)

            implementation(libs.coil.compose)
            implementation(libs.coil.network)

            implementation(libs.androidx.lifecycle.viewmodel.ktx)*//*
        }*/
    }
}

android {
    namespace = "ru.braveowlet.simple_mvi_example.features.dogs_screen.impl"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig { minSdk = libs.versions.android.minSdk.get().toInt() }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    dependencies { debugImplementation(libs.compose.ui.tooling) }

    buildFeatures {

        compose  = true
        buildConfig = true
    }
}
