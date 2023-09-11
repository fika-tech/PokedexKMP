package tech.fika.pokedex.local.settings

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.NSUserDefaultsSettings
import platform.Foundation.NSUserDefaults
import tech.fika.pokedex.local.settings.SettingsFactory
import tech.fika.pokedex.local.settings.SettingsHolder

class UserDefaultsSettingsFactory(
    private val delegate: NSUserDefaults,
) : SettingsFactory {
    override fun create(): SettingsHolder = SettingsHolder(settings = NSUserDefaultsSettings(delegate))
}

@OptIn(ExperimentalSettingsImplementation::class)
class KeychainSettingsFactory(
    private val service: String,
) : SettingsFactory {
    override fun create(): SettingsHolder = SettingsHolder(settings = KeychainSettings(service = service))
}
