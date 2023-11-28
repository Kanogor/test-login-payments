package ru.kanogor.testlogin_payments.domain.usecase

import okhttp3.RequestBody
import ru.kanogor.testlogin_payments.domain.entity.ApiRepository
import ru.kanogor.testlogin_payments.domain.entity.ReceivedPostInfo

class PostLoginUseCase(val repository: ApiRepository) {
    suspend fun execute(requestBody: RequestBody): ReceivedPostInfo {
        return repository.postLoginInfo(requestBody = requestBody)
    }

}