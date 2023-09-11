package tech.fika.pokedex.application

import android.content.Context
import android.os.Build.MANUFACTURER
import android.os.Build.MODEL
import android.os.Build.VERSION

actual class Platform(val context: Context) {
    actual val operatingSystem: OperatingSystem = OperatingSystem.Android
    actual val osVersion: String = VERSION.RELEASE
    actual val device: String = "$MANUFACTURER $MODEL"
}
