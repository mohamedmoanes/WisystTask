package com.developnetwork.wisysttask.modules

import com.developnetwork.wisysttask.BuildConfig
import com.developnetwork.wisysttask.data.network.Service
import okhttp3.*
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


val appModules = module {
    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService<Service>(
            okHttpClient = createHttpClient(),
            baseUrl = BuildConfig.SERVER_URL
        )
    }

}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
//    val interceptor = HttpLoggingInterceptor()
//    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(headersInterceptor)
        .readTimeout(50, TimeUnit.SECONDS)
        .writeTimeout(50, TimeUnit.SECONDS)
        .connectTimeout(50, TimeUnit.SECONDS)
//        .addInterceptor(interceptor)
//        .addNetworkInterceptor(StethoInterceptor())
        .build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

private val headersInterceptor: Interceptor = object : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("api_email", "api.auth@hs.info")
            .addFormDataPart("api_password", "Ka@r%*MoAJ!rtPXz")
            .addFormDataPart("lang", "en")
            .build()

        request = request.newBuilder()
            .addHeader("Accept", "application/json")
            .post(requestBody)
            .build()
        return chain.proceed(request)
    }
}