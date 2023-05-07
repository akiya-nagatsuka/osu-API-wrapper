package com.github.nagatsukaakiya.osuapi.auth

import com.github.nagatsukaakiya.osuapi.redirectUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TokenRequest(
    @SerialName("client_id")
    val clientId: String = com.github.nagatsukaakiya.osuapi.clientId,

    @SerialName("client_secret")
    val clientSecret: String = com.github.nagatsukaakiya.osuapi.clientSecret,

    @SerialName("grant_type")
    val grantType: GrantType,

    @SerialName("code")
    val code: String? = null,

    @SerialName("redirect_uri")
    val redirectUri: String? = null,

    @SerialName("refresh_token")
    val refreshToken: String? = null,

    @SerialName("scope")
    val scope: TokenScope? = null
)

@Serializable
enum class GrantType {

    @SerialName("authorization_code")
    AuthorizationCode,

    @SerialName("refresh_token")
    RefreshToken,
}

@Serializable
enum class TokenScope(val value: String) {

    @SerialName("chat.write")
    ChatWrite("chat.write"),

    @SerialName("delegate")
    Delegate("delegate"),

    @SerialName("forum.write")
    ForumWrite("forum.write"),

    @SerialName("friends.read")
    FriendsRead("friends.read"),

    @SerialName("identity")
    Identity("identity"),

    @SerialName("public")
    Public("public"),

    @SerialName("lazer")
    Lazer("lazer"),
}
