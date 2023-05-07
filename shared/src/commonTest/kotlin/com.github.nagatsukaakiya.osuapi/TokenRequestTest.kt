package com.github.nagatsukaakiya.osuapi

import com.github.nagatsukaakiya.osuapi.auth.TokenRequest
import com.github.nagatsukaakiya.osuapi.auth.TokenScope
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenRequestTest {
    @Test
    fun serialize_chat_write() {
        val res = Json.encodeToString(TokenRequest(scope = TokenScope.ChatWrite))

        assertEquals("{\"scope\":\"chat.write\"}", res)
    }

    @Test
    fun serialize_forum_write() {
        val res = Json.encodeToString(TokenRequest(scope = TokenScope.ForumWrite))

        assertEquals("{\"scope\":\"forum.write\"}", res)
    }

    @Test
    fun serialize_delegate() {
        val res = Json.encodeToString(TokenRequest(scope = TokenScope.Delegate))

        assertEquals("{\"scope\":\"delegate\"}", res)
    }

    @Test
    fun serialize_friends_read() {
        val res = Json.encodeToString(TokenRequest(scope = TokenScope.FriendsRead))

        assertEquals("{\"scope\":\"friends.read\"}", res)
    }

    @Test
    fun serialize_identity() {
        val res = Json.encodeToString(TokenRequest(scope = TokenScope.Identity))

        assertEquals("{\"scope\":\"identity\"}", res)
    }

    @Test
    fun serialize_lazer() {
        val res = Json.encodeToString(TokenRequest(scope = TokenScope.Lazer))

        assertEquals("{\"scope\":\"lazer\"}", res)
    }

    @Test
    fun serialize_public() {
        val res = Json.encodeToString(TokenRequest(scope = TokenScope.Public))

        assertEquals("{\"scope\":\"public\"}", res)
    }
}
