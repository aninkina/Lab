package dev.fstudio.weather.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.fstudio.weather.api.OpenWeatherApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val URL = "https://api.openweathermap.org/data/2.5/"

@ExperimentalSerializationApi
val networkModule = module {
    single { provideOkHttp(get()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single { provideLastAlert(androidApplication()) }
}

private fun provideOkHttp(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .cache(Cache(context.cacheDir, 10485760L))
        .followRedirects(true)
        .followSslRedirects(true)
        .addInterceptor(Interceptor {
            val request = it.request()
            val url = request.url.newBuilder()
                .addQueryParameter("appid", "f747c7257813a7127a2d9de58c58ae94").build()
            it.proceed(request.newBuilder().url(url).build())
        })
        .build()
}

@ExperimentalSerializationApi
private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URL)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
}

private fun provideApiService(retrofit: Retrofit): OpenWeatherApi {
    return retrofit.create(OpenWeatherApi::class.java)
}

fun provideLastAlert(androidApplication: Application): SharedPreferences{
    return  androidApplication.getSharedPreferences("key_event",  Context.MODE_PRIVATE)
}