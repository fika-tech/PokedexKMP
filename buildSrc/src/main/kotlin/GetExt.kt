fun get(key: String): String? = System.getProperty(key) ?: System.getenv(key)

fun get(key: String, default: String) = get(key) ?: default