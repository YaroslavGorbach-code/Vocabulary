package yaroslavgorbach.koropapps.vocabulary.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date?.isToday(): Boolean {
    val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return if (this != null) {
        df.format(this).equals(df.format(Date()))
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

fun Date.formatDD(): String {
    val format = SimpleDateFormat("dd", Locale.getDefault())
    return format.format(time)
}