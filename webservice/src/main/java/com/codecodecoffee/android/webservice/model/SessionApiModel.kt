package com.codecodecoffee.android.webservice.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SessionApiModel(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String
) {
}