package ru.kanogor.testlogin_payments.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.kanogor.testlogin_payments.domain.entity.ErrorGetInfo
import ru.kanogor.testlogin_payments.domain.entity.ReceivedGetInfo
import ru.kanogor.testlogin_payments.domain.entity.ResponseInfo

@JsonClass(generateAdapter = true)
data class ReceivedGetInfoDto(
    @Json(name = "success")
    override val success: String,
    @Json(name = "response")
    override val response: List<ResponseInfoDto>?,
    @Json(name = "error")
    override val error: ErrorGetInfoDto?
) : ReceivedGetInfo

@JsonClass(generateAdapter = true)
data class ErrorGetInfoDto(
    @Json(name = "error_msg")
    override val errorMsg: String
) : ErrorGetInfo

@JsonClass(generateAdapter = true)
data class ResponseInfoDto(
    @Json(name = "id")
    override val id: Int,
    @Json(name = "title")
    override val title: String,
    @Json(name = "amount")
    override val amount: Any?,
    @Json(name = "created")
    override val created: Int?
) : ResponseInfo
