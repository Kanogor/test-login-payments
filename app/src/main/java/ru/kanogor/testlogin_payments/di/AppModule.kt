package ru.kanogor.testlogin_payments.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kanogor.testlogin_payments.presentation.LoginViewModel

val appModule = module {

    viewModel {
        LoginViewModel(repository = get())
    }

}