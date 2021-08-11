package yaroslavgorbach.koropapps.vocabulary.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date?.isToday(): Boolean {
    val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    return if (this != null) {
        val currentDate = df.format(this)
        this.equals(currentDate)
    } else {
        false
    }

}

fun Date?.dayOfWeek(): String {
    val df = SimpleDateFormat("EE", Locale.getDefault())

    return if (this != null) {
        df.format(this).uppercase()
    } else {
        "?"
    }
}