package ru.kanogor.testlogin_payments.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.kanogor.testlogin_payments.data.ApiRepositoryImpl
import ru.kanogor.testlogin_payments.data.retrofit.SearchApi
import ru.kanogor.testlogin_payments.domain.entity.ApiRepository

private const val BASE_URL = "https://easypay.world/api-test/"

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

}