package com.codecodecoffee.android.core.account

import androidx.security.crypto.EncryptedSharedPreferences
import com.codecodecoffee.android.webservice.AuthService
import com.greyblocks.gatekeeper.GateKeeper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManagerImpl @Inject constructor(
    private val gateKeeper: GateKeeper,
    private val encryptedSharedPreferences: EncryptedSharedPreferences,
    private val authService: AuthService
) : SessionManager {
    override suspend fun login(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        gateKeeper.logout()
        //TODO
    }


}