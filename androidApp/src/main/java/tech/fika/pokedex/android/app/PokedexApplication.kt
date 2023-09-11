package tech.fika.pokedex.android.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant

@HiltAndroidApp
class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        if (BuildConfig.DEBUG) {
//            plant(Timber.DebugTree())
//        }
    }
}
