package com.example.myapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.myapplication.auth.TokenProviderImpl

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val code = intent?.data?.getQueryParameter("code")
        setContent {
            var text by remember { mutableStateOf("Loading...") }
            if (code != null) {
                LaunchedEffect(true) {
                    text = try {
                        TokenProviderImpl().apply { this.code = code }.getToken()
                    } catch (e: Exception) {
                        "Failed: ${e.message}"
                    }
                }
            }
            Column {
                Text(text)
            }
        }
    }
}