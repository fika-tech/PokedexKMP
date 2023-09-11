package tech.fika.pokedex.local.settings

import android.content.SharedPreferences
import com.russhwolf.settings.SharedPreferencesSettings
import tech.fika.pokedex.local.settings.SettingsFactory
import tech.fika.pokedex.local.settings.SettingsHolder

class SharedPreferencesSettingsFactory(
    private val delegate: SharedPreferences,
) : SettingsFactory {
    override fun create(): SettingsHolder = SettingsHolder(settings = SharedPreferencesSettings(delegate = delegate))
}
