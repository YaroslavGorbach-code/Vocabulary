package yaroslavgorbach.koropapps.vocabulary.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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

fun getTimeAgo(duration: Long): String? {
    val now = Date()
    val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - duration)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - duration)
    val hours = TimeUnit.MILLISECONDS.toHours(now.time - duration)
    val days = TimeUnit.MILLISECONDS.toDays(now.time - duration)
    return if (seconds < 60) {
        "just now"
    } else if (minutes == 1L) {
        "a minute ago"
    } else if (minutes in 2..59) {
        "$minutes minutes ago"
    } else if (hours == 1L) {
        "an hour ago"
    } else if (hours in 2..23) {
        "$hours hours ago"
    } else if (days == 1L) {
        "a day ago"
    } else {
        "$days days ago"
    }
}