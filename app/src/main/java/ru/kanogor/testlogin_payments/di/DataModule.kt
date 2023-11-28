package ru.kanogor.testlogin_payments.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.kanogor.testlogin_payments.data.retrofit.ApiRepositoryImpl
import ru.kanogor.testlogin_payments.data.retrofit.SearchApi
import ru.kanogor.testlogin_payments.domain.entity.ApiRepository

private const val BASE_URL = "https://easypay.world/api-test/"
private const val SHARED_PREFERENCE_NAME = "preference_db_name"

val dataModule = module {

    single<ApiRepository> {
        ApiRepositoryImpl(searchApi = get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    factory {
        get<Retrofit>().create(SearchApi::class.java)
    }

    single { provideSharedPref(androidApplication()) }


}

fun provideSharedPref(app: Application): SharedPreferences {
    return app.applicationContext.getSharedPreferences(
        SHARED_PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )
}