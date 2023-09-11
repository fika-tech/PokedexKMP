import app.cash.sqldelight.gradle.SqlDelightExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.accessors.dm.LibrariesForDeps
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.kodein.mock.gradle.MocKMPGradlePlugin

val Project.deps: LibrariesForDeps get() = (this as ExtensionAware).extensions.getByType(LibrariesForDeps::class.java)

fun Project.kotlin(block: KotlinMultiplatformExtension.() -> Unit) = extensions.getByType<KotlinMultiplatformExtension>().block()

fun Project.androidCommon(block: CommonExtension<*, *, *, *>.() -> Unit) = extensions.getByType(CommonExtension::class.java).block()

fun Project.androidLibrary(block: LibraryExtension.() -> Unit) = extensions.getByType(LibraryExtension::class.java).block()

fun Project.androidApp(block: BaseAppModuleExtension.() -> Unit) = extensions.getByType(BaseAppModuleExtension::class.java).block()

fun Project.androidBase(block: BaseExtension.() -> Unit) = extensions.getByType(BaseExtension::class.java).block()

fun Project.ktlint(block: KtlintExtension.() -> Unit) = extensions.getByType(KtlintExtension::class.java).block()

fun Project.publications(block: PublishingExtension.() -> Unit) = extensions.getByType<PublishingExtension>().block()

fun Project.sqlDelight(block: SqlDelightExtension.() -> Unit) = extensions.getByType<SqlDelightExtension>().block()

fun Project.mockmp(block: MocKMPGradlePlugin.Extension.() -> Unit) = extensions.getByType<MocKMPGradlePlugin.Extension>().block()

fun Project.kapt(block: KaptExtension.() -> Unit) = extensions.getByType<KaptExtension>().block()