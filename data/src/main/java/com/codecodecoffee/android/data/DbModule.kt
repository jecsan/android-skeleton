package com.codecodecoffee.android.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DbModule {



    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
      return  Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
    }

}