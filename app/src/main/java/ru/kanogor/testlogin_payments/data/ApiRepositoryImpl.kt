package ru.kanogor.testlogin_payments.data

import okhttp3.RequestBody
import ru.kanogor.testlogin_payments.data.retrofit.SearchApi
import ru.kanogor.testlogin_payments.domain.entity.ApiRepository
import ru.kanogor.testlogin_payments.domain.entity.ReceivedGetInfo
import ru.kanogor.testlogin_payments.domain.entity.ReceivedPostInfo

class ApiRepositoryImpl(val searchApi: SearchApi) : ApiRepository {
    override suspend fun getInfo(): ReceivedGetInfo {
        return searchApi.getLoginInfo().body()!!
    }

    override suspend fun postInfo(loginPassword: RequestBody): ReceivedPostInfo {
        return searchApi.postLoginInfo(loginPassword).body()!!
    }


}