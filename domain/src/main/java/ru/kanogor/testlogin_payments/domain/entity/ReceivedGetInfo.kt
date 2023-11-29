package ru.kanogor.testlogin_payments.domain.entity

interface ReceivedGetInfo {
    val success: String
    val response: List<ResponseInfo>?
    val error: ErrorInfo?
}

interface ResponseInfo {
    val id: Int
    val title: String
    val amount: String?
    val created: Long?
}
