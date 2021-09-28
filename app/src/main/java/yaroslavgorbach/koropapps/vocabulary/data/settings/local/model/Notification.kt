package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notification(
    var isActive: Boolean = true,
    var hour: Int = 12,
    var minute: Int = 30,
    var text: String = ""
) : Parcelable