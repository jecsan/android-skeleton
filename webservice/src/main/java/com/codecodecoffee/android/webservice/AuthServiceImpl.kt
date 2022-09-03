package com.codecodecoffee.android.webservice

import com.codecodecoffee.android.webservice.model.SessionApiModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthServiceImpl @Inject constructor(@Named(WebServiceModule.AUTH_CLIENT) private val httpClient: HttpClient) :
    AuthService {


    override suspend fun refreshAccessToken(refreshToken: String): SessionApiModel {
        return httpClient.post("/auth/refresh") {
            parameter("refresh_token", refreshToken)
        }.body()
    }

    override suspend fun login(username: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun register(username: String, password: String) {
        TODO("Not yet implemented")
    }
}