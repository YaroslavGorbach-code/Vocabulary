package yaroslavgorbach.koropapps.vocabulary.data.settings.local.factory

import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification

class NotificationFactory {
    fun create(
        isActive: Boolean,
        hour: Int,
        minute: Int,
        text: String,
    ): Notification {
        return Notification(
            isActive = isActive,
            hour = hour,
            minute = minute,
            text = text,
        )
    }
}