package tech.fika.pokedex.application

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.stringWithCString
import platform.UIKit.UIDevice
import platform.posix.uname
import platform.posix.utsname

actual class Platform {
    actual val operatingSystem: OperatingSystem = OperatingSystem.Ios
    actual val osVersion: String = UIDevice.currentDevice.systemVersion
    actual val device: String = "Apple $deviceModel"
}

@OptIn(ExperimentalForeignApi::class)
private val deviceModel: String
    get() = memScoped {
        val systemInfo: utsname = alloc()
        uname(systemInfo.ptr)
        NSString.stringWithCString(cString = systemInfo.machine, encoding = NSUTF8StringEncoding)
    }.toString()
