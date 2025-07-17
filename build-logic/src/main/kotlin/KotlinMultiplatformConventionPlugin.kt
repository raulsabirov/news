import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget()
                iosX64()
                iosArm64()
                iosSimulatorArm64()

                sourceSets.apply {
                    commonMain.dependencies {
                        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
                        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
                        implementation("io.insert-koin:koin-core:4.0.0")
                        implementation("io.ktor:ktor-client-core:2.3.9")
                    }
                }
            }
        }
    }
}
