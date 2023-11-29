package ru.kanogor.testlogin_payments.data.retrofit

import okhttp3.RequestBody
import ru.kanogor.testlogin_payments.domain.entity.ApiRepository
import ru.kanogor.testlogin_payments.domain.entity.ReceivedGetInfo
import ru.kanogor.testlogin_payments.domain.entity.ReceivedPostInfo

class ApiRepositoryImpl(val searchApi: SearchApi) : ApiRepository {
    override suspend fun getInfo(token: String): ReceivedGetInfo {
        return searchApi.getInfo(token).body()!!
    }

    override suspend fun postLoginInfo(requestBody: RequestBody): ReceivedPostInfo {
        return searchApi.postLoginInfo(requestBody).body()!!
    }


}