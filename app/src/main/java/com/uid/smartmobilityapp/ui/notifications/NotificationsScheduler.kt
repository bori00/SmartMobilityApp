package com.uid.smartmobilityapp.ui.notifications

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.SystemClock
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.uid.smartmobilityapp.MainActivity.Companion.context
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.notifications.model.IntentNotificationsQueue
import com.uid.smartmobilityapp.ui.notifications.model.NotificationChannelTypes
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


class NotificationsScheduler {

    fun setupChannels() {
        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        for (notificationChannelData in NotificationChannelTypes.values()) {
            val mChannel = NotificationChannel(
                notificationChannelData.data.channelId,
                notificationChannelData.data.channelTitle,
                notificationChannelData.data.importance
            )
            mChannel.description = notificationChannelData.data.channelDescription
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mNotificationManager!!.createNotificationChannel(mChannel)
        }
    }

    fun scheduleNotifications() {

        setupChannels()

        for (intentNotification in IntentNotificationsQueue.notifications) {

            val intent = Intent(context, NotificationPublisher::class.java)
            intent.putExtra(NotificationPublisher.EXTRA_TITLE, intentNotification.intentName)
            intent.putExtra(NotificationPublisher.EXTRA_CONTENT, intentNotification.message)
            intent.putExtra(NotificationPublisher.EXTRA_CHANNEL_ID, intentNotification.channel.data.channelId)
            intent.putExtra(NotificationPublisher.EXTRA_NOTIFICATION_ID, intentNotification.id)
            Log.d("NotificationPublisher", "Send intent " + intent.getStringExtra(NotificationPublisher.EXTRA_CHANNEL_ID))

            val pending =
                PendingIntent.getBroadcast(context, intentNotification.id, intent, PendingIntent.FLAG_MUTABLE)

            // Schedule notification
            val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + (ChronoUnit.MILLIS.between(LocalDateTime.now(), intentNotification.notificationTime)),
                pending)
        }
    }
}