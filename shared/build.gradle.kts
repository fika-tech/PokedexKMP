setupKotlinMultiplatform(
    buildTargets = listOf(
        BuildTarget.Ios, BuildTarget.Android(namespace = "pokedex.shared")
    ),
    additionalPlugins = listOf(
        deps.plugins.parcelize,
        deps.plugins.serialization,
        deps.plugins.sqldelight,
    )
)

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementations(
                deps.kotlinx.coroutinesCore,
                deps.kotlinx.serializationJson,
                deps.essenty.parcelable,
                deps.macaron.core,
                deps.macaron.statemachine,
                deps.ktor.contentNegotiation,
                deps.ktor.client.core,
                deps.ktor.serialization,
                deps.ktor.logging,
                deps.sqldelight.runtime,
                deps.settings.core,
                deps.sqldelight.coroutineExtensions,
                deps.sqldelight.primitiveAdapters,
                deps.settings.serialization,
            )
            apis(
                deps.logging.kermit,
            )
        }
        androidMain.dependencies {
            implementations(
                deps.ktor.client.android,
                deps.sqldelight.androidDriver,
            )
        }
        iosMain.dependencies {
            implementations(
                deps.ktor.client.ios,
                deps.sqldelight.nativeDriver,
            )
        }
        commonTest.dependencies {
            implementations(
                deps.test.kotlin,
                deps.test.coroutinesTest,
                deps.test.turbine,
                deps.test.kotest,
                deps.ktor.client.mock,
                deps.settings.test,
            )
        }
        androidTest.dependencies {
            implementations(deps.sqldelight.sqliteDriver)
        }
    }
}

sqlDelight {
    databases {
        create("PokemonDatabase") {
            packageName.set("tech.fika.pokedex.local.database")
        }
    }
}
