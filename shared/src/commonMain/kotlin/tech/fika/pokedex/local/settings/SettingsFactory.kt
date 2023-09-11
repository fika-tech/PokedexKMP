package tech.fika.pokedex.local.settings

fun interface SettingsFactory {
    fun create(): SettingsHolder
}
