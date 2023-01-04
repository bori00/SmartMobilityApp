package com.uid.smartmobilityapp.ui.notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.notifications.model.IntentNotification


class NotificationPublisher : BroadcastReceiver() {
    companion object {
        var EXTRA_TITLE = "title"
        var EXTRA_CONTENT = "content"
        var EXTRA_CHANNEL_ID = "channelId"
        var EXTRA_NOTIFICATION_ID = "notificationId"
    }

    override fun onReceive(context: Context, intent: Intent) {

        val notification: Notification = getNotification(
            context,
            intent.getStringExtra(EXTRA_TITLE),
            intent.getStringExtra(EXTRA_CONTENT),
            intent.getStringExtra(EXTRA_CHANNEL_ID))

        val id = intent.getIntExtra(EXTRA_NOTIFICATION_ID, 0)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(id, notification)
    }

    private fun getNotification(context: Context, title: String?, content: String?, channelId: String?): Notification {
        Log.d("NotificationPublisher", "ChannelId: $channelId title: $title content: $content")
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channelId!!)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.setSmallIcon(R.drawable.ic_baseline_schedule_24)
        builder.setChannelId(channelId)
        return builder.build()
    }
}