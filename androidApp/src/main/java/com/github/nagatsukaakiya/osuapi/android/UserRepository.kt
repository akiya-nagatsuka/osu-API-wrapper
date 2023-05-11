package com.github.nagatsukaakiya.osuapi.android

import com.github.nagatsukaakiya.osuapi.auth.Token
import com.github.nagatsukaakiya.osuapi.auth.TokenProvider
import com.github.nagatsukaakiya.osuapi.models.Score
import com.github.nagatsukaakiya.osuapi.users.UsersApi

class UserRepository(
    private val tokenProvider: TokenProvider,
    private val usersApi: UsersApi,
) {
    suspend fun getMyBestScores(): List<Score> {
        val token = tokenProvider.getTokenByRefresh()
        val myId = usersApi.getOwnData(Token(token)).id
        return usersApi.getUserScores(Token(token), myId, "best", limit = 100)
    }
}
