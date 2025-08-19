import com.android.build.api.dsl.ApplicationExtension
import com.example.news.buildlogic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
                
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
            }
        }
    }
}
