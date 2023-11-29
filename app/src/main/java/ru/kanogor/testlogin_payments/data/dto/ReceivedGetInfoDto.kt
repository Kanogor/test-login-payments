package ru.kanogor.testlogin_payments.data.dto

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.kanogor.testlogin_payments.domain.entity.ReceivedGetInfo
import ru.kanogor.testlogin_payments.domain.entity.ResponseInfo
import java.lang.reflect.Type

@JsonClass(generateAdapter = true)
data class ReceivedGetInfoDto(
    @Json(name = "success")
    override val success: String,
    @Json(name = "response")
    override val response: List<ResponseInfoDto>?,
    @Json(name = "error")
    override val error: ErrorInfoDto?
) : ReceivedGetInfo

@JsonClass(generateAdapter = true)
data class ResponseInfoDto(
    @Json(name = "id")
    override val id: Int,
    @Json(name = "title")
    override val title: String,
    @Json(name = "amount")
    @JsonAdapter(ToStringDeserializer::class)
    override val amount: String?,
    @Json(name = "created")
    override val created: Long?
) : ResponseInfo


class ToStringDeserializer : JsonDeserializer<String> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): String {
        return json.toString()
    }
}
