package com.uid.smartmobilityapp.ui.notifications.model

import android.app.NotificationManager

enum class NotificationChannelTypes(val data: NotificationChannelData) {
    REGULAR_INTENT_NOTIFICATIONS_CHANNEL(NotificationChannelData(
        "regular-intent-notifications-channel",
        "Regular Intent Notifications",
        "Updates about when to depart",
        NotificationManager.IMPORTANCE_DEFAULT
    )),
    FLEXIBLE_INTENT_NOTIFICATIONS_CHANNEL(NotificationChannelData(
        "flexible-intent-notifications-channel",
        "Flexible Intent Notifications",
        "Updates about when to depart",
        NotificationManager.IMPORTANCE_DEFAULT)),
}