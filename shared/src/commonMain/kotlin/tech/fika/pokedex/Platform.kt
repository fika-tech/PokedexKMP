package tech.fika.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform