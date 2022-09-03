package com.codecodecoffee.android.core

import androidx.security.crypto.EncryptedSharedPreferences
import com.codecodecoffee.android.webservice.AccessTokenProvider
import com.codecodecoffee.android.webservice.AuthService
import com.codecodecoffee.android.webservice.AuthServiceImpl
import com.codecodecoffee.android.webservice.WebServiceModule
import com.greyblocks.gatekeeper.GateKeeper
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
class AccessTokenProviderImpl @Inject constructor(
    private val gateKeeper: GateKeeper,
    private val encryptedSharedPreferences: EncryptedSharedPreferences,
    private val authService: Provider<AuthService>
) :
    AccessTokenProvider {
    override fun getAccessToken(): String? {
        TODO()
    }

    override suspend fun refreshToken(): String {
        val refreshToken = encryptedSharedPreferences.getString(
            Constants.REFRESH_TOKEN,
            ""
        )!!
        return authService.get().refreshAccessToken(refreshToken).accessToken
    }


}