package ru.kanogor.testlogin_payments.domain.entity

import okhttp3.RequestBody

interface ApiRepository {

    suspend fun getInfo(token: String): ReceivedGetInfo

    suspend fun postLoginInfo(requestBody: RequestBody): ReceivedPostInfo

}