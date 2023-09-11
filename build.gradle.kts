setupTestCoverage()
setupKotlinCompile()

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
