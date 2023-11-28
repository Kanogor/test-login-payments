package ru.kanogor.testlogin_payments.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.kanogor.testlogin_payments.domain.entity.ErrorPostInfo
import ru.kanogor.testlogin_payments.domain.entity.ReceivedPostInfo
import ru.kanogor.testlogin_payments.domain.entity.ResponseToken

@JsonClass(generateAdapter = true)
data class ReceivedPostInfoDto(
    @Json(name = "success")
    override val success: String,
    @Json(name = "error")
    override val error: ErrorPostPostInfoDto?,
    @Json(name = "response")
    override val response: ResponseTokenDto?
) : ReceivedPostInfo

@JsonClass(generateAdapter = true)
data class ErrorPostPostInfoDto(
    @Json(name = "error_msg")
    override val errorMsg: String
) : ErrorPostInfo

@JsonClass(generateAdapter = true)
data class ResponseTokenDto(
    @Json(name = "token")
    override val token: String
) : ResponseToken

