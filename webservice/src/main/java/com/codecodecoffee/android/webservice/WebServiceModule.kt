package com.codecodecoffee.android.webservice

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WebServiceModule {


    const val AUTH_CLIENT = "auth_client"
    const val DEFAULT_CLIENT = "default_client"


    @Provides
    @Singleton
    fun providesJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
            encodeDefaults = true
        }
    }

    @Provides
    @Singleton
    @Named(AUTH_CLIENT)
    fun provideAuthHttpClient(
        json: Json,
        tokenProvider: AccessTokenProvider
    ): HttpClient {
        return HttpClient() {
            developmentMode = BuildConfig.DEBUG

            install(ContentNegotiation) {
                json(json)
            }
            install(ContentEncoding) {
                deflate(1.0F)
                gzip(0.9F)
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            defaultRequest {

                url(BuildConfig.BASE_URL)
                header("Content-Type", "application/json")
                header("Accept", "application/json")
                header("Accept-Encoding", "gzip, deflate")

                tokenProvider.getAccessToken()?.let {
                    header("Authorization", it)
                }
            }
        }
    }

    @Provides
    @Singleton
    @Named(DEFAULT_CLIENT)
    fun provideHttpClient(
        json: Json,
        tokenProvider: AccessTokenProvider
    ): HttpClient {
        return HttpClient() {
            developmentMode = BuildConfig.DEBUG
            install(Auth) {
                bearer {
                    refreshTokens {
                        tokenProvider.refreshToken()?.let {
                            BearerTokens("", it)
                        }
                    }
                    loadTokens {
                        tokenProvider.getAccessToken()?.let {
                            BearerTokens(it, "")
                        }
                    }
                }
            }
            install(ContentNegotiation) {
                json(json)
            }
            install(Resources) {
            }
            install(HttpTimeout) {
            }
            install(HttpCache) {
            }

            install(ContentEncoding) {
                deflate(1.0F)
                gzip(0.9F)
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            defaultRequest {

                url(BuildConfig.BASE_URL)
                header("Content-Type", "application/json")
                header("Accept", "application/json")
                header("Accept-Encoding", "gzip, deflate")

                tokenProvider.getAccessToken()?.let {
                    header("Authorization", it)
                }
            }
        }
    }

}


@Module
@InstallIn(SingletonComponent::class)
interface WebServiceBindings {


    @Binds
    @Singleton
    fun bindAuthService(authServiceImpl: AuthServiceImpl): AuthService
}