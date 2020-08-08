package com.moanes.wisysttask.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.moanes.wisysttask.modules.appModules
import com.moanes.wisysttask.modules.repoModule
import com.moanes.wisysttask.modules.viewModelModule
import com.moanes.wisysttask.utils.LocaleManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WisystApplication : Application() {
    companion object {
        lateinit var appContext: Context
        lateinit var localeManager: LocaleManager
//        lateinit var sharedPreferencesManager: SharedPreferencesManager
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
//        sharedPreferencesManager = SharedPreferencesManager()
        startKoin {
            androidContext(this@WisystApplication)
            modules(listOf(appModules, repoModule, viewModelModule))
        }
//        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else ExceptionReportingTree())
    }

    override fun attachBaseContext(base: Context) {
        localeManager = LocaleManager(base)
        super.attachBaseContext(localeManager.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeManager.setLocale(this)
    }
}