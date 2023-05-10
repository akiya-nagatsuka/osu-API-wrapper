package com.github.nagatsukaakiya.osuapi.android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.nagatsukaakiya.osuapi.auth.TokenProvider
import com.github.nagatsukaakiya.osuapi.auth.activityCt
import org.koin.android.ext.android.inject

class LaunchActivity : ComponentActivity() {
    private val tokenProvider: TokenProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCt = this
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var text by remember { mutableStateOf("Loading") }
                    LaunchedEffect(true) {
                        text = try {
                            if (tokenProvider.isAuthenticated) {
                                this@LaunchActivity.startActivity(
                                    Intent(this@LaunchActivity, MainActivity::class.java)
                                )
                            } else {
                                tokenProvider.setCredentials(clientId, clientSecret, redirectUrl)
                                tokenProvider.authorise(redirectUrlFormatted)
                            }
                            "Success"
                        } catch (e: Exception) {
                            e.localizedMessage ?: "error"
                        }
                    }
                    GreetingView(text)
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
