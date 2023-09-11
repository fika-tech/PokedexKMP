import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.withType
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@OptIn(ExperimentalKotlinGradlePluginApi::class)
fun Project.setupKotlinMultiplatform(
    buildTargets: List<BuildTarget>,
    additionalPlugins: List<Provider<PluginDependency>> = listOf(),
    additionalConfig: KotlinMultiplatformExtension.() -> Unit = {},
    ) {
    plugins.apply(deps.plugins.kotlinMultiplatform)

    buildTargets.setup<BuildTarget.Android> {
        setupKmpAndroidLibrary(namespace)
    }

    setupLinter()
    additionalPlugins.forEach(plugins::apply)

    kotlin {
        targetHierarchy.default()

        targets.withType<KotlinNativeTarget> {
            compilations.all {
                kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
            }
        }

        buildTargets.setup<BuildTarget.Js> {
            js(IR) {
                useCommonJs()
                browser()
            }
        }

        buildTargets.setup<BuildTarget.Android> {
            androidTarget {
                publishAllLibraryVariants()
                compilations.all {
                    kotlinOptions {
                        jvmTarget = Config.jvmTarget
                    }
                }
            }
        }

        buildTargets.setup<BuildTarget.Jvm> {
            jvm()
        }

        buildTargets.setup<BuildTarget.Ios> {
            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            ).forEach {
                it.binaries.framework {
                    baseName = "shared"
                }
            }
        }

        buildTargets.setup<BuildTarget.MacOsX64> {
            macosX64()
        }

        sourceSets {
//            buildTargets.setup<BuildTarget.Ios> {
//                iosSimulatorArm64Main.dependsOn(iosMain)
//                iosSimulatorArm64Test.dependsOn(iosTest)
//            }
            all {
                languageSettings.apply {
                    optIn("kotlin.RequiresOptIn")
                    optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                }
            }
        }

        additionalConfig()
    }
}
