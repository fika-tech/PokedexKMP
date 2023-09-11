import org.gradle.api.Project

fun Project.setupJetpackCompose() {
    androidCommon {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = deps.versions.composeCompiler.get()
        }
    }
}
