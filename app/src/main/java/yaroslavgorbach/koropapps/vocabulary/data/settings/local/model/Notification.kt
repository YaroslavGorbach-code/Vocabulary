package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Notification(
    var isActive: Boolean = true,
    var hour: Int = 12,
    var minute: Int = 30,
    var text: String = getNotificationDefaultText()
) : Parcelable {

    val scheduledTime: Long
        get() {
            var time = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
            }.timeInMillis - System.currentTimeMillis()

            if (time < 0) {
                time = 1L
            }

            return time
        }
}

fun getNotificationDefaultText(): String {
    Log.i("localeLogg", Locale.getDefault().country)
    return if (Locale.getDefault().country == "RU"
        || Locale.getDefault().country == "UA"
    ) {
        "Прилшо время поговорить"
    } else {
        "Time to speak"
    }
}