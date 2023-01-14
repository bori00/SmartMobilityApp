package com.uid.smartmobilityapp.ui.notifications

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.UserActivity
import com.uid.smartmobilityapp.ui.notifications.model.IntentNotification
import com.uid.smartmobilityapp.ui.notifications.model.NotificationChannelTypes


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
        builder.setAutoCancel(true)

        // set destination on notification click
        val destinationIntent = Intent(context, UserActivity::class.java)
        val destination : Int;
        if (channelId == NotificationChannelTypes.REGULAR_INTENT_NOTIFICATIONS_CHANNEL.data.channelId) {
            destination = R.id.nav_regular_intents
        } else {
            destination = R.id.nav_flexible_intents
        }
        destinationIntent.putExtra(UserActivity.destinationFragmentIdExtraName, destination)
        // Create an Intent for the activity you want to start
        val resultIntent = PendingIntent.getActivity(context, 0, destinationIntent, PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_ONE_SHOT);

        builder.setContentIntent(resultIntent)
        return builder.build()
    }
}