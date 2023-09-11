import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

/* Kotlin */
fun KotlinDependencyHandler.implementations(vararg dependencies: Provider<MinimalExternalModuleDependency>) = dependencies.forEach(::implementation)
fun KotlinDependencyHandler.apis(vararg dependencies: Provider<MinimalExternalModuleDependency>) = dependencies.forEach(::api)

/* Android */
fun DependencyHandler.implementation(dependency: Provider<MinimalExternalModuleDependency>) = add("implementation", dependency)
fun DependencyHandler.implementations(vararg dependencies: Provider<MinimalExternalModuleDependency>) = dependencies.forEach(::implementation)
fun DependencyHandler.project(module: String): Dependency? = add("implementation", project(module))
fun DependencyHandler.projects(vararg modules: String) = modules.forEach { add("implementation", project(it)) }
fun DependencyHandler.projectApi(module: String): Dependency? = add("api", project(module))
fun DependencyHandler.kapt(dependency: Provider<MinimalExternalModuleDependency>): Dependency? = add("kapt", dependency)

