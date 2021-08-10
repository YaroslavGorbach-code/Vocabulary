package yaroslavgorbach.koropapps.vocabulary.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.isToday(): Boolean {
    val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = df.format(Calendar.getInstance().time)
    return this.equals(currentDate)
}