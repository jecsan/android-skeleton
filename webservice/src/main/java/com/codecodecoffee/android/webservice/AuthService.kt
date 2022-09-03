package com.codecodecoffee.android.webservice

import com.codecodecoffee.android.webservice.model.SessionApiModel

interface AuthService {


    suspend fun refreshAccessToken(refreshToken:String) : SessionApiModel

    suspend fun login(username:String, password:String)

    suspend fun logout()

    suspend fun register(username:String, password:String)
}