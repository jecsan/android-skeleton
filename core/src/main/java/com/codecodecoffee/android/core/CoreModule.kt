package com.codecodecoffee.android.core

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.codecodecoffee.android.core.account.SessionManager
import com.codecodecoffee.android.core.account.SessionManagerImpl
import com.codecodecoffee.android.webservice.AccessTokenProvider
import com.greyblocks.gatekeeper.GateKeeper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object CoreModule {

    @Provides
    @Singleton
    fun provideGateKeeper(@ApplicationContext context: Context): GateKeeper {
        return GateKeeper(context)
    }


    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): EncryptedSharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            "encrypted_shared_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

}


@Module
@InstallIn(SingletonComponent::class)
abstract class AccountModuleBindings {

    @Binds
    @Singleton
    abstract fun bindAccessTokenProvider(accessTokenProvider: AccessTokenProviderImpl): AccessTokenProvider


    @Binds
    @Singleton
    abstract fun bindSessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

}
