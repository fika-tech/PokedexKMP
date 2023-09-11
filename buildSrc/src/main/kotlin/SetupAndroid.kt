import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.plugins
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.setupAndroidApp(
    namespace: String,
    additionalPlugins: List<Provider<PluginDependency>> = listOf(),
    additionalConfig: BaseAppModuleExtension.() -> Unit = {},
) {
    plugins.apply(deps.plugins.kotlinAndroid)
    plugins.apply(deps.plugins.androidApp)
    additionalPlugins.forEach(plugins::apply)

    setupAndroidCore(_namespace = namespace)

    androidApp {
        defaultConfig {
            versionCode = 1
            versionName = get(Config.versionKey, Config.defaultVersion)
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }

        packagingOptions {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        sourceSets {
            all {
                java.srcDir("src/${name}/kotlin")
            }
        }

        additionalConfig()
    }
}

fun Project.setupAndroidLibrary(
    namespace: String,
    additionalConfig: LibraryExtension.() -> Unit = {},
) {
    plugins.apply(deps.plugins.kotlinAndroid)
    plugins.apply(deps.plugins.androidLibrary)

    setupAndroidCore(_namespace = namespace)
    androidLibrary {
        buildTypes {
            getByName(Config.release) {
                isMinifyEnabled = false
            }
        }

        additionalConfig()
    }
}

fun Project.setupKmpAndroidLibrary(
    namespace: String,
    additionalConfig: LibraryExtension.() -> Unit = {},
) {
    plugins.apply(deps.plugins.androidLibrary)

    setupAndroidCore(_namespace = namespace)
    androidLibrary {
        buildTypes {
            getByName(Config.release) {
                isMinifyEnabled = false
            }
        }

        additionalConfig()
    }
}

private fun Project.setupAndroidCore(_namespace: String) {
    androidCommon {
        namespace = _namespace
        buildToolsVersion = Config.Android.buildToolsVersion
        compileSdk = Config.Android.compileSdk

        defaultConfig {
            minSdk = Config.Android.minSdk
            testInstrumentationRunner = Config.Android.instrumentedTestRunner
        }

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
            }
        }

        compileOptions {
            sourceCompatibility = Config.javaVersion
            targetCompatibility = Config.javaVersion
        }

        packagingOptions {
            resources.excludes += setOf(
                "META-INF/*",
                "META-INF/gradle/incremental.annotation.processors",
            )
        }
    }

    tasks.withType(KotlinCompile::class.java) {
        kotlinOptions {
            jvmTarget = Config.javaVersion.toString()
            freeCompilerArgs += listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-Xcontext-receivers",
                "-Xskip-prerelease-check",
            )
        }
    }

    setupLinter()
    setupTestCoverage()
}
