package com.example.myapplication.auth

import android.app.Activity
import android.content.Intent
import android.net.Uri

actual fun webAuthenticate(url: String) {
    activityCt.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

lateinit var activityCt: Activity
