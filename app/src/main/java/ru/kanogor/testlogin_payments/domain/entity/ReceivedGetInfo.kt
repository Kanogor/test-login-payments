package ru.kanogor.testlogin_payments.domain.entity

interface ReceivedGetInfo {

    val success: String
    val response: List<ResponseInfo>

}

interface ResponseInfo {
    val id: Int
    val title: String
    val amount: Any?
    val created: Int?
}
