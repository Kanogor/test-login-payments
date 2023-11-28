package ru.kanogor.testlogin_payments.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import ru.kanogor.testlogin_payments.domain.entity.ApiRepository

class LoginViewModel(val repository: ApiRepository) : ViewModel() {

    fun getInfo() {
        val jsonObject = JSONObject()
        jsonObject.put("login", "demo")
        jsonObject.put("password", "12345")

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        Log.d("LoginVW", "paramObject = $requestBody")
        viewModelScope.launch {
            kotlin.runCatching {
                repository.postInfo(requestBody)
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