package com.example.myapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.myapplication.auth.Token
import com.example.myapplication.auth.TokenProvider
import com.example.myapplication.auth.TokenProviderImpl
import com.example.myapplication.beatmaps.BeatmapsApi
import com.example.myapplication.chat.repository.ChatRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.get

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.data?.getQueryParameter("code")?.let {
            val tokenProvider: TokenProvider by inject()
            tokenProvider.code = it
        }
        val beatmapsApi: BeatmapsApi by inject()
        val tokenProvider: TokenProvider by inject()

        setContent {
            val coroutineScope = rememberCoroutineScope()
            var text by remember { mutableStateOf("") }

            Column {
                Button(onClick = {
                    text = "Loading..."
                    coroutineScope.launch {
                        text = try {
                            val token = Token(tokenProvider.getToken())
                            with(beatmapsApi) {
//                                token.lookup()
                            }
//                            with(token) {
//                                beatmapsApi.lookup()
//                            }
//                            val chat = beatmapsApi.lookup()
//                            chat.toString()
                            ""
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