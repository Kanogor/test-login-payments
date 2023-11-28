package ru.kanogor.testlogin_payments.domain.entity

interface ReceivedGetInfo {

    val success: String
    val response: List<ResponseInfo>?
    val error: ErrorGetInfo?
}

interface ErrorGetInfo {
    val errorMsg: String
}

interface ResponseInfo {
    val id: Int
    val title: String
    val amount: Any?
    val created: Int?
}
