package com.codecodecoffee.android.webservice

interface AccessTokenProvider {

    fun getAccessToken(): String?
    suspend fun refreshToken(): String?

}