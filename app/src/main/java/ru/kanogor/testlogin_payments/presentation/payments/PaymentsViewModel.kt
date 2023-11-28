package ru.kanogor.testlogin_payments.presentation.payments

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kanogor.testlogin_payments.domain.usecase.GetInfoUseCase
import ru.kanogor.testlogin_payments.presentation.TOKEN_KEY


class PaymentsViewModel(
    val getInfoUseCase: GetInfoUseCase,
    val pref: SharedPreferences
) : ViewModel() {


    private fun getDataValueString(): String? {
        return pref.getString(TOKEN_KEY, null)
    }

    private fun isTokenAdded(): Boolean {
        return pref.contains(TOKEN_KEY)
    }

    fun getInfo() {
        if (isTokenAdded()) {
            val token = getDataValueString()
            viewModelScope.launch {
                kotlin.runCatching {
                    getInfoUseCase.execute(token!!)
                }.fold(
                    onSuccess = {
                        Log.d("LoginVW", "onSuccess = $it")
                    },
                    onFailure = {
                        Log.d("LoginVW", "onFailure = ${it.message}")
                    }
                )
            }
        }
    }

}