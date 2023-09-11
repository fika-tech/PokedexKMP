package tech.fika.pokedex.local.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.set
import kotlinx.serialization.ExperimentalSerializationApi

data class SettingsHolder(val settings: Settings) {
    @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
    inline fun <reified T : Any> getOrNull(settingKey: SettingKey<T>): T? = when (settingKey) {
        is SettingKey.Serializable -> settings.decodeValueOrNull(serializer = settingKey.serializer, key = settingKey.name)
        is SettingKey.String -> settings.getStringOrNull(key = settingKey.name) as? T
        is SettingKey.Int -> settings.getIntOrNull(key = settingKey.name) as? T
        is SettingKey.Float -> settings.getLongOrNull(key = settingKey.name) as? T
        is SettingKey.Long -> settings.getFloatOrNull(key = settingKey.name) as? T
        is SettingKey.Double -> settings.getDoubleOrNull(key = settingKey.name) as? T
        is SettingKey.Boolean -> settings.getBooleanOrNull(key = settingKey.name) as? T
    }

    @Throws(IllegalStateException::class)
    inline operator fun <reified T : Any> get(key: SettingKey<T>): T = getOrNull(key) ?: throw IllegalStateException()

    @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
    inline operator fun <reified T : Any> set(settingKey: SettingKey<T>, value: T?) {
        if (value != null) {
            when (settingKey) {
                is SettingKey.Serializable -> settings.encodeValue(serializer = settingKey.serializer, key = settingKey.name, value = value)
                else -> settings.set(key = settingKey.name, value = value)
            }
        }
    }

    inline operator fun minus(settingKey: SettingKey<*>) {
        //  https://github.com/russhwolf/multiplatform-settings/issues/81
        when (settingKey) {
            is SettingKey.Serializable<*> -> settings.keys.filter { it.startsWith(prefix = settingKey.name) }.forEach(settings::remove)
            else -> settings.remove(key = settingKey.name)
        }
    }

    fun remove(vararg settingKeys: SettingKey<*>) {
        settingKeys.forEach(::minus)
    }

    fun removeAll() = settings.clear()

    fun hasKey(settingKey: SettingKey<*>): Boolean = when (settingKey) {
        is SettingKey.Serializable<*> -> settings.keys.any { it.startsWith(prefix = settingKey.name) }
        else -> settings.hasKey(key = settingKey.name)
    }
}
