package com.codecodecoffee.android.skeletion

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.greyblocks.gatekeeper.Gate
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import javax.inject.Inject


@HiltAndroidApp
class AndroidSkeletonApplication : Application(), Gate, Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()
    override fun getGateClass() = UnauthenticatedActivity::class.java

    override fun onCreate() {
//        setupNotificationChannel()
    }

    private fun setupNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = ""
            val descriptionText = ""
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel("", name, importance)

            channel.description = descriptionText
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

}