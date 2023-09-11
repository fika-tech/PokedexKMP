import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.Properties

fun Project.getProperty(key: String): String = System.getenv(key)
    ?: localProperties.getProperty(key)
    ?: error("Missing [$key] property in local.properties or environment variables")

val Project.localProperties: Properties
    get() = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "local.properties")))
    }
