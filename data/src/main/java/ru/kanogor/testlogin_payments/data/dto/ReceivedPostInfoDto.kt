package ru.kanogor.testlogin_payments.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.kanogor.testlogin_payments.domain.entity.ReceivedPostInfo
import ru.kanogor.testlogin_payments.domain.entity.ResponseToken

@JsonClass(generateAdapter = true)
data class ReceivedPostInfoDto(
    @Json(name = "success")
    override val success: String,
    @Json(name = "error")
    override val error: ErrorInfoDto?,
    @Json(name = "response")
    override val response: ResponseTokenDto?
) : ReceivedPostInfo

@JsonClass(generateAdapter = true)
data class ResponseTokenDto(
    @Json(name = "token")
    override val token: String
) : ResponseToken

