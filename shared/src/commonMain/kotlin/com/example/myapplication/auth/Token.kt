package com.example.myapplication.auth

data class Token(val value: String, val scopes: String = "")
