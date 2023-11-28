package ru.kanogor.testlogin_payments.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kanogor.testlogin_payments.presentation.login.LoginViewModel
import ru.kanogor.testlogin_payments.presentation.payments.PaymentsViewModel

val appModule = module {

    viewModel {
        LoginViewModel(
            postLoginUseCase = get(),
            pref = get()
        )
    }

    viewModel {
        PaymentsViewModel(
            getInfoUseCase = get(),
            pref = get()
        )
    }

}