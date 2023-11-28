package ru.kanogor.testlogin_payments.data.retrofit

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.kanogor.testlogin_payments.data.LoginPassword
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
        "v: 1",
        "token: 7b7c0a690bee2e8d90512ed1b57e19f0"
    )
    @GET("payments")
    suspend fun getLoginInfo(): Response<ReceivedGetInfoDto>

}