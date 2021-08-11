package yaroslavgorbach.koropapps.vocabulary.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.isToday(): Boolean {
    val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = df.format(this)
    return this.equals(currentDate)
}

fun Date.day(): String {
    val df = SimpleDateFormat("EE", Locale.getDefault())
    return df.format(this).uppercase()
}