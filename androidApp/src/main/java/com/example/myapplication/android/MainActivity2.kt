package com.example.myapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.myapplication.auth.TokenProvider
import com.example.myapplication.auth.TokenProviderImpl
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
        val chatRepository: ChatRepository by inject()

        setContent {
            val coroutineScope = rememberCoroutineScope()
            var text by remember { mutableStateOf("") }

            Column {
                Button(onClick = {
                    text = "Loading..."
                    coroutineScope.launch {
                        text = try {
                            val chat = chatRepository.newChat(3, "Hi!")
                            chat.toString()
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