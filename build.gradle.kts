import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
/*
plugins {

    id("com.android.application") version "8.10.0" apply false
    id("com.android.library") version "8.10.0" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20" apply false
    id 'org.jetbrains.kotlin.jvm' version '1.8.10' apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    id 'org.jetbrains.kotlin.multiplatform' version '2.1.20' apply false
    id 'com.android.kotlin.multiplatform.library' version '8.10.0' apply false
// this version matches your Kotlin version
    //  id 'org.jetbrains.kotlin.jvm' version '1.8.10' apply false
}
*/

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.ksp) apply false
 //   alias(libs.plugins.room) apply false
    alias(libs.plugins.detekt)
}

/*
tasks.withType(KotlinCompile::class.java).all {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}
*/

/*plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
}*/


/*task clean(type: Delete) {
    delete rootProject.buildDir
}*/

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
