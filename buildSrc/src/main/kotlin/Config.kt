import org.gradle.api.JavaVersion

object Config {
    const val group = "tech.fika"
    const val versionKey = "VERSION"
    const val defaultVersion = "SNAPSHOT"
    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"
    const val release = "release"
    const val debug = "debug"

    object Android {
        const val minSdk = 24 // Android 7.0
        const val targetSdk = 34 // Android 13.0
        const val compileSdk = targetSdk
        const val buildToolsVersion = "34.0.0"
        const val instrumentedTestRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
