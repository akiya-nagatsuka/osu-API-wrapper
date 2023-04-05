package com.example.myapplication.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TokenRequest(
    @SerialName("client_id")
    val clientId: String = com.example.myapplication.clientId,
    @SerialName("client_secret")
    val clientSecret: String = com.example.myapplication.clientSecret,
    @SerialName("code")
    val code: String,
    @SerialName("grant_type")
    val grantType: String = "authorization_code",
    @SerialName("redirect_uri")
    val redirectUri: String = com.example.myapplication.redirectUrl,
)
