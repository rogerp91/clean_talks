package com.github.rogerp91.android.cleantalks

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.github.rogerp91.android.cleantalks.source.local.CacheLibrary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.random.Random

/**
 * Created by rpatino on oct/2019
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        CacheLibrary.init(this)
        startKoin { androidContext(this@App) }
        val nightMode = when (Random.nextBoolean()) {
            true -> AppCompatDelegate.MODE_NIGHT_YES
            false -> AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}