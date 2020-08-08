package com.moanes.wisysttask.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
import android.os.Build.VERSION_CODES.N
import androidx.preference.PreferenceManager
import java.util.*

class LocaleManager(context: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    val language: String
        get() {
            val prf = prefs.getString(LANGUAGE_KEY, null)
            if (prf == null) {
                val locale: Locale

                if (Build.VERSION.SDK_INT >= N)
                    locale = Resources.getSystem().configuration.locales.get(0)
                else
                    locale = Resources.getSystem().configuration.locale

                return locale.language.toLowerCase()
            } else {
                return prf
            }
        }

    val isEnglish: Boolean
        get() {
            return LANGUAGE_ENGLISH == language
        }

    fun setLocale(c: Context): Context {
        return updateResources(c, language)
    }

    fun setNewLocale(c: Context, language: String): Context {
        persistLanguage(language)
        return updateResources(c, language)
    }

    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(language: String) {
        prefs.edit().putString(LANGUAGE_KEY, language).commit()
    }

    private fun updateResources(context: Context, language: String): Context {
        var context = context
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context.resources
        val config = Configuration(res.configuration)
        if (Build.VERSION.SDK_INT >= JELLY_BEAN_MR1) {
            config.setLocale(locale)
            config.setLayoutDirection(locale)
            context = context.createConfigurationContext(config)
            res.updateConfiguration(config, res.displayMetrics)
        } else {
            config.locale = locale
            res.updateConfiguration(config, res.displayMetrics)
            config.setLayoutDirection(locale)
        }
        return context
    }

    companion object {

        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_Arabic = "ar"
        private const val LANGUAGE_KEY = "language_key"

        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return if (Build.VERSION.SDK_INT >= N) {
                config.locales.get(0)
            } else config.locale
        }

        fun getLocale(lang: String): Locale {
            return Locale(lang)
        }
    }
}