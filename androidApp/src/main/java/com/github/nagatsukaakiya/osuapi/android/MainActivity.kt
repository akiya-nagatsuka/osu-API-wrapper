package com.github.nagatsukaakiya.osuapi.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.github.nagatsukaakiya.osuapi.auth.Token
import com.github.nagatsukaakiya.osuapi.auth.TokenProvider
import com.github.nagatsukaakiya.osuapi.auth.TokenScope
import com.github.nagatsukaakiya.osuapi.beatmaps.BeatmapsApi
import com.github.nagatsukaakiya.osuapi.news.NewsApi
import com.github.nagatsukaakiya.osuapi.ranking.RankingApi
import com.github.nagatsukaakiya.osuapi.models.GameMode
import com.github.nagatsukaakiya.osuapi.models.RankingType
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.data?.getQueryParameter("code")?.let {
            val tokenProvider: TokenProvider by inject()
            tokenProvider.setCode(it)
        }
        val beatmapsApi: BeatmapsApi by inject()
        val tokenProvider: TokenProvider by inject()
        val newsApi: NewsApi by inject()
        val rankingApi: RankingApi by inject()

        setContent {
            val coroutineScope = rememberCoroutineScope()
            var text by remember { mutableStateOf("") }

            Column {
                Button(onClick = {
                    text = "Loading..."
                    coroutineScope.launch {
                        text = try {
                            with(Token(tokenProvider.getTokenByRefresh(TokenScope.Public))) {
                                rankingApi.getRanking(this, GameMode.Standard, RankingType.Performance).toString()
                            }
                        } catch (e: Exception) {
                            "Failed: ${e.cause}, ${e.message}"
                        }
                    }
                }) {
                    Text("Send message")
                }
                Text(text)
            }
        }
    }
}