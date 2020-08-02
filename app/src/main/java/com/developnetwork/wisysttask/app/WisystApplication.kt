package com.developnetwork.wisysttask.app

import android.app.Application
import com.developnetwork.wisysttask.modules.appModules
import com.developnetwork.wisysttask.modules.repoModule
import com.developnetwork.wisysttask.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WisystApplication : Application() {
//    companion object {
//        lateinit var appContext: Context
//        lateinit var localeManager: LocaleManager
//        lateinit var sharedPreferencesManager: SharedPreferencesManager
//    }

    override fun onCreate() {
        super.onCreate()

//        appContext = applicationContext
//        sharedPreferencesManager = SharedPreferencesManager()
        startKoin {
            androidContext(this@WisystApplication)
            modules(listOf(appModules, repoModule, viewModelModule))
        }
//        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else ExceptionReportingTree())
    }

//    override fun attachBaseContext(base: Context) {
//        localeManager = LocaleManager(base)
//        super.attachBaseContext(localeManager.setLocale(base))
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        localeManager.setLocale(this)
//    }
}