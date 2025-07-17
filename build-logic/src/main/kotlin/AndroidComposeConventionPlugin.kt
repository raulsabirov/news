import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            val extension = extensions.getByName("android") as CommonExtension<*, *, *, *, *, *>
            
            extension.apply {
                buildFeatures {
                    compose = true
                }
                
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }

            dependencies {
                add("implementation", "androidx.activity:activity-compose:1.9.0")
                add("implementation", "androidx.compose.ui:ui:1.7.0-beta01")
                add("implementation", "androidx.compose.material3:material3:1.2.1")
                add("implementation", "androidx.compose.ui:ui-tooling-preview:1.7.0-beta01")
            }
        }
    }
}
