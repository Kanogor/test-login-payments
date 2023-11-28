package ru.kanogor.testlogin_payments.di

import org.koin.dsl.module
import ru.kanogor.testlogin_payments.domain.usecase.GetInfoUseCase
import ru.kanogor.testlogin_payments.domain.usecase.PostLoginUseCase

val domainModule = module {

    factory {
        PostLoginUseCase(repository = get())
    }

    factory {
        GetInfoUseCase(repository = get())
    }

}