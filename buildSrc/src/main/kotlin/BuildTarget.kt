interface BuildTarget {
    interface NonNative : BuildTarget
    interface Native : BuildTarget
    interface Darwin : Native
    interface Linux : Native
    data class Android(val namespace: String) : NonNative
    object Jvm : NonNative
    object Js : NonNative
    object Ios : Darwin
    object MacOsX64 : Darwin
    object LinuxX64 : Linux
}

internal inline fun <reified T : BuildTarget> List<BuildTarget>.setup(block: T.() -> Unit) = this.filterIsInstance<T>().forEach { it.block() }
