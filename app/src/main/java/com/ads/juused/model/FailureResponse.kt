package com.ads.juused.model

class FailureResponse {

    private var errorCode = 0
    private lateinit var errorMessage: CharSequence
    private lateinit var responseType: CharSequence

    fun getResponseType(): CharSequence? {
        return responseType
    }

    fun setResponseType(responseType: CharSequence?) {
        this.responseType = responseType!!
    }

    fun FailureResponse() {}

    fun FailureResponse(errorCode: Int, errorMessage: CharSequence?) {
        this.errorCode = errorCode
        this.errorMessage = errorMessage!!
    }

    fun getErrorCode(): Int {
        return errorCode
    }

    fun setErrorCode(errorCode: Int) {
        this.errorCode = errorCode
    }

    fun getErrorMessage(): CharSequence? {
        return errorMessage
    }

    fun setErrorMessage(errorMessage: String?) {
        this.errorMessage = errorMessage!!
    }
}