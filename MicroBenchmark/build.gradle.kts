plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.benchmark)
    alias(libs.plugins.kotlinAndroid)
    id("news.android.compose")
}

android {
    namespace = "com.example.microbenchmark"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        targetSdk = 36
        multiDexEnabled  = true
        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
    }

    testBuildType = "debug"
    buildTypes {
        debug {
            // Since isDebuggable can"t be modified by gradle for library modules,
            // it must be done in a manifest - see src/androidTest/AndroidManifest.xml
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "benchmark-proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            isDefault = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
  //  targetProjectPath = ":app"
}

dependencies {
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.benchmark.junit4)

    implementation(project(":app")){
        exclude(group = "androidx.activity")
        exclude(group = "androidx.compose.ui")
        exclude(group = "androidx.compose.material3")
        exclude(group = "androidx.compose.foundation")
        exclude(group = "androidx.compose.runtime")
        exclude(group = "androidx.lifecycle")
        exclude(group = "androidx.core")
    }
//    implementation(projects.features.articlesScreens.articlesImpl)
  //  implementation("androidx.benchmark:benchmark-junit4:1.2.2")
    implementation("androidx.compose.ui:ui-test-junit4:1.5.4")
  //  implementation("androidx.test.ext:junit:1.1.5")
    implementation("androidx.test.espresso:espresso-core:3.5.1")
    // Add your dependencies here. Note that you cannot benchmark code
    // in an app module this way - you will need to move any code you
    // want to benchmark to a library module:
    // https://developer.android.com/studio/projects/android-library#Convert

}