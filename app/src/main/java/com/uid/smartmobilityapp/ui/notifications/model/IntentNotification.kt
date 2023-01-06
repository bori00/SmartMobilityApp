package com.uid.smartmobilityapp.ui.notifications.model

import java.time.LocalDateTime

data class IntentNotification (val id: Int,
                               val intentName: String,
                               val message: String,
                               val channel: NotificationChannelTypes,
                               val notificationTime: LocalDateTime
)