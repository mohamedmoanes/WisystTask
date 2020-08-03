package com.moanes.wisysttask.utils

import com.moanes.wisysttask.R
import com.moanes.wisysttask.app.WisystApplication
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

private fun responseErrorHandler(response: String, responsecode: Int): String {
    val context = WisystApplication.appContext.applicationContext
    return when {
        responsecode < 500 -> {
//            if (responsecode == 401) {
//                WisystApplication.sharedPreferencesManager.isLoggedIn = false
//                val i = Intent(this, AuthActivity::class.java)
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(i)
//            }
            try {
                val responseObject = JSONObject(response)
//                if (responseObject.has("data")) {
//                    return responseObject.getString("data")
//                }
//                if (responseObject.has("errors")) {
//                    val dataObject = responseObject.getJSONObject("errors")
//                    if (dataObject.has("email"))
//                        return dataObject.getJSONArray("email").getString(0)
//                }
                if (responseObject.has("msg")) {
                    val error = responseObject.get("msg")
                    if (error is String)
                        return error
                    if (error is JSONObject) {
                        for (key: String in error.keys().iterator()) {
                            return error.getJSONArray(key).getString(0)
                        }
                    }
                }
                responseObject.getString("message")
            } catch (e: Exception) {
                if (null != e.message)
                    e.message!!
                else
                    context.getString(R.string.error)

            }
        }
        responsecode == 500 -> {
            context.getString(R.string.server_error)
        }
        else -> {
            context.getString(R.string.error)
        }
    }
}

private fun failureHandler(t: Throwable): String {
    val context = WisystApplication.appContext.applicationContext
    return if (t is IOException) {
        context.getString(R.string.no_internet)
    } else {
        context.getString(R.string.error)
    }
}

fun errorHandler(throwable: Throwable): String? {
    try {
        return if (throwable is HttpException)
            responseErrorHandler(throwable.response()!!.errorBody()!!.string(), throwable.code())
        else failureHandler(throwable)
    } catch (e: Exception) {
        return WisystApplication.appContext.applicationContext.getString(R.string.error)
    }
    return null
}