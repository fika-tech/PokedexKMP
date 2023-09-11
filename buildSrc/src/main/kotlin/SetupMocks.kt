import org.gradle.api.Project

fun Project.setupMocks() {
    plugins.apply(deps.plugins.mockmp)
    mockmp {
        usesHelper = true
    }
}
