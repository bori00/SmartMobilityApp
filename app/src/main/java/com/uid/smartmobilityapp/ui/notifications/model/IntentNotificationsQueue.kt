package com.uid.smartmobilityapp.ui.notifications.model

import com.uid.smartmobilityapp.ui.my_regular_intents.model.MyRegularIntents
import java.time.LocalDateTime

object IntentNotificationsQueue {
    val notifications: ArrayList<IntentNotification> = arrayListOf(
        IntentNotification(1, "Get Groceries", "You should depart at 14:20, by car, to spend the least time in traffic. Expected arrival time: 14:29", NotificationChannelTypes.FLEXIBLE_INTENT_NOTIFICATIONS_CHANNEL, LocalDateTime.now().plusSeconds(3)),
        IntentNotification(2, MyRegularIntents.regularIntents.get(0), "You should depart at 16:25 to arrive on time", NotificationChannelTypes.REGULAR_INTENT_NOTIFICATIONS_CHANNEL, LocalDateTime.now().plusSeconds(15)),
    )
}