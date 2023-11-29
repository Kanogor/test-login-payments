package ru.kanogor.testlogin_payments.presentation.payments

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kanogor.testlogin_payments.domain.entity.ResponseInfo
import ru.kanogor.testlogin_payments.domain.usecase.GetInfoUseCase
import ru.kanogor.testlogin_payments.presentation.TOKEN_KEY


class PaymentsViewModel(
    val getInfoUseCase: GetInfoUseCase,
    val pref: SharedPreferences
) : ViewModel() {


    private val _responseInfo = MutableStateFlow<List<ResponseInfo>?>(null)
    val responseInfo = _responseInfo.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private fun getDataValueString(): String? {
        return pref.getString(TOKEN_KEY, null)
    }

    private fun isTokenAdded(): Boolean {
        return pref.contains(TOKEN_KEY)
    }

    fun exitAccount() {
        pref.edit().remove(TOKEN_KEY).apply()
    }

    private fun getInfo() {
        if (isTokenAdded()) {
            _isLoading.value = true
            val token = getDataValueString()
            viewModelScope.launch {
                kotlin.runCatching {
                    getInfoUseCase.execute(token!!)
                }.fold(
                    onSuccess = { info ->
                        if (info.success == "true") {
                            _responseInfo.value = info.response
                            Log.d("PaymentsVW", "onSuccess = $info")
                        } else {
                            Log.d("PaymentsVW", "onSuccess error= ${info.error?.errorMsg}")
                        }
                    },
                    onFailure = {
                        Log.d("PaymentsVW", "onFailure = ${it.message}")
                    }
                )
                _isLoading.value = false
            }
        }
    }

    init {
        getInfo()
    }

    fun refresh() {
        getInfo()
    }

}