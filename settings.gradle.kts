@file:Suppress("UnstableApiUsage")

import org.gradle.internal.impldep.org.bouncycastle.its.asn1.EndEntityType.app
import java.awt.SystemColor.desktop

include(":MicroBenchmark")



enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
/* для
dependencies {
    implementation(projects.features.login)
    implementation(projects.libraries.ui)
}
*
*
*/

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}
rootProject.name = "News"

include(":app")
include(":lib")
include(":lib2")
include(":mylibrary")

include(":app-desktop")


// CORE
include(":core:network")
include(":core:database")
include(":core:recources")



include(":features:articles-screens:articles-impl")
include(":MicroBenchmark")
