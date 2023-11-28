package ru.kanogor.testlogin_payments.domain.entity

interface ReceivedPostInfo {

    val success: String
    val response: ResponseToken?
    val error: ErrorInfo?

}

interface ErrorInfo {
    val errorMsg: String
}

interface ResponseToken {
    val token: String
}

