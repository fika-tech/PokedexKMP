pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("deps") {
            from(files("config/deps.versions.toml"))
        }
    }
}

rootProject.name = "pokedex-kmp"
include(":androidApp")
include(":shared")