package ru.kanogor.testlogin_payments.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.kanogor.testlogin_payments.domain.entity.ErrorInfo

@JsonClass(generateAdapter = true)
data class ErrorInfoDto(
    @Json(name = "error_msg")
    override val errorMsg: String
) : ErrorInfo