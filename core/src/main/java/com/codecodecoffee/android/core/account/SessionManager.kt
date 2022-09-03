package com.codecodecoffee.android.core.account

interface SessionManager {


    suspend fun login(email: String, password: String)
    suspend fun logout()

}