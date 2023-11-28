package ru.kanogor.testlogin_payments.domain.usecase

import ru.kanogor.testlogin_payments.domain.entity.ApiRepository
import ru.kanogor.testlogin_payments.domain.entity.ReceivedGetInfo

class GetInfoUseCase(val repository: ApiRepository) {

    suspend fun execute(token: String): ReceivedGetInfo {
        return repository.getInfo(token = token)
    }

}