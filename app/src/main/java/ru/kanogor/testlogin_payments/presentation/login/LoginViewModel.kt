package ru.kanogor.testlogin_payments.presentation.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import ru.kanogor.testlogin_payments.domain.usecase.PostLoginUseCase
import ru.kanogor.testlogin_payments.presentation.TOKEN_KEY

class LoginViewModel(
    val postLoginUseCase: PostLoginUseCase,
    val pref: SharedPreferences
) : ViewModel() {

    private fun saveData(value: String) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(TOKEN_KEY, value)
        editor.apply()
    }

    private val _isDataLoading = MutableStateFlow<Boolean>(false)
    val isDataLoading = _isDataLoading.asStateFlow()

    private val _isSuccess = MutableLiveData<Boolean?>()
    val isSuccess: LiveData<Boolean?> = _isSuccess


    fun postLoginPassword(login: String, password: String) {
        _isDataLoading.value = true
        _isSuccess.value = null
        val jsonObject = JSONObject()
        jsonObject.put("login", login)
        jsonObject.put("password", password)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        Log.d("LoginVW", "paramObject = $requestBody")
        viewModelScope.launch {
            kotlin.runCatching {
                postLoginUseCase.execute(requestBody)
            }.fold(
                onSuccess = { info ->
                    if (info.success == "true") {
                        val token = info.response!!.token
                        Log.d("LoginVW", "onSuccess token = $token")
                        saveData(value = token)
                        _isSuccess.value = true
                    } else {
                        _isSuccess.value = false
                        Log.d("LoginVW", "onSuccess false = ${info.error?.errorMsg}")
                    }
                },
                onFailure = {
                    _isSuccess.value = false
                    Log.d("LoginVW", "onFailure = ${it.message}")
                }
            )
            _isDataLoading.value = false
        }
    }

}