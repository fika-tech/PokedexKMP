package tech.fika.pokedex.local.settings

import kotlinx.serialization.KSerializer

sealed interface SettingKey<T> {
    abstract class Serializable<K>(val serializer: KSerializer<K>) : SettingKey<K>
    interface String : SettingKey<kotlin.String>
    interface Int : SettingKey<kotlin.Int>
    interface Long : SettingKey<kotlin.Long>
    interface Float : SettingKey<kotlin.Float>
    interface Double : SettingKey<kotlin.Double>
    interface Boolean : SettingKey<kotlin.Boolean>

    val name: kotlin.String get() = this::class.toString()
}
