package io.chthonic.codprob

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.chthonic.codprob.util.ReleaseTree
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(
            if (BuildConfig.DEBUG) {
                Timber.DebugTree()
            } else {
                ReleaseTree()
            }
        )
    }

}
