package tech.fika.pokedex.application

expect class Platform {
    val operatingSystem: OperatingSystem
    val osVersion: String
    val device: String
}
