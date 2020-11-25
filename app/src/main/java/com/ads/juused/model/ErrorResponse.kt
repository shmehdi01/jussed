package com.ads.juused.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("success")
    val success: String,
    @SerializedName("error")
    val error: ErrorBody
)

data class ErrorBody(
    @SerializedName("message")
    val message: String,
    @SerializedName("stack")
    val stack: String
)