package ru.kanogor.testlogin_payments.data.retrofit

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.kanogor.testlogin_payments.data.dto.ReceivedGetInfoDto
import ru.kanogor.testlogin_payments.data.dto.ReceivedPostInfoDto

interface SearchApi {

    @Headers(
        "app-key: 12345",
        "v: 1"
    )
    @POST("login")
    suspend fun postLoginInfo(@Body requestBody: RequestBody): Response<ReceivedPostInfoDto>

    @Headers(
        "app-key: 12345",
        "v: 1"
    )
    @GET("payments")
    suspend fun getInfo(@Header("token") token: String): Response<ReceivedGetInfoDto>


}