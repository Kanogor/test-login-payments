package ru.kanogor.testlogin_payments.domain.entity

import okhttp3.RequestBody
import ru.kanogor.testlogin_payments.data.LoginPassword

interface ApiRepository {

    suspend fun getInfo() : ReceivedGetInfo

    suspend fun postInfo(loginPassword: RequestBody) : ReceivedPostInfo

}