@file:Suppress("DSL_SCOPE_VIOLATION")

import org.jetbrains.compose.desktop.application.dsl.TargetFormat



plugins {
    alias(libs.plugins.kotlinMultiplatform)
    application
}


kotlin {
    jvm {
        withJava()
    }

    sourceSets {

        val jvmMain by getting {
            dependencies {
              //  implementation(projects.)
                implementation(kotlin("stdlib"))

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)
                implementation(project(":core:network"))
                implementation(projects.features.articlesScreens.articlesImpl)
                implementation(libs.androidx.lifecycle.viewmodel.ktx)
          //      implementation(project(":compose-ui"))

            //    implementation(compose.desktop.currentOs)
           //     implementation(libs.decompose.extensionsComposeJetbrains)
            }
        }
    }
}

application {
    mainClass.set("com/example/myapplication/desktop/Main.kt")
}

/*
compose.desktop {
    application {
        mainClass = "com.example.myapplication.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinMultiplatformComposeDesktopApplication"
            packageVersion = libs.versions.project.get()
        }
    }
}
*/
