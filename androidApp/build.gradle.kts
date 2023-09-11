setupAndroidApp(
    namespace = "pokedex.android",
    additionalPlugins = listOf(
        deps.plugins.kotlinKapt,
        deps.plugins.hilt
    )
)
setupJetpackCompose()

dependencies {
    project(":shared")
    implementations(
        deps.kotlinx.coroutinesCore,
        deps.kotlinx.coroutinesAndroid,
        deps.androidx.activityCompose,
        deps.androidx.navigationUi,
        deps.androidx.navigationFragment,
        deps.androidx.savedState,
        deps.macaron.core,
        deps.macaron.statemachine,
        deps.macaron.logging,
        deps.macaron.statesaver,
        deps.compose.runtime,
        deps.compose.material,
        deps.compose.material3,
        deps.compose.ui,
        deps.compose.uiGraphics,
        deps.compose.uiTooling,
        deps.compose.uiToolingPreview,
        deps.compose.material,
        deps.compose.foundation,
        deps.compose.foundationLayout,
        deps.coil.core,
        deps.coil.compose,
        deps.hilt.android,
        deps.logging.timber,
        deps.accompanist.webview,
    )
    kapt(deps.hilt.compiler)
}

kapt {
    correctErrorTypes = true
}
