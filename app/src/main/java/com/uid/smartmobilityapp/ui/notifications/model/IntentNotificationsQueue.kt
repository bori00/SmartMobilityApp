package com.uid.smartmobilityapp.ui.notifications.model

import java.time.LocalDateTime

object IntentNotificationsQueue {
    val notifications: ArrayList<IntentNotification> = arrayListOf(
        IntentNotification(1, "Get Groceries", "You should depart at 17:30 to have the most ecological ride. Expected arrival time: 17:45", NotificationChannelTypes.FLEXIBLE_INTENT_NOTIFICATIONS_CHANNEL, LocalDateTime.now().plusSeconds(20)),
        IntentNotification(2, "Go to DBD class", "You should depart at 16:25 to arrive on time", NotificationChannelTypes.REGULAR_INTENT_NOTIFICATIONS_CHANNEL, LocalDateTime.now().plusMinutes(3)),
    )
}