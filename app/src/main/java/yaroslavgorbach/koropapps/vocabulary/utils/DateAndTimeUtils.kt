package yaroslavgorbach.koropapps.vocabulary.utils

import java.text.DateFormat
import java.text.ParseException
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

fun Date.formatDefault(): String {
    val dateFormat: DateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault())
    return dateFormat.format(this)
}

fun Date.formatDD(): String {
    val format = SimpleDateFormat("dd", Locale.getDefault())
    return format.format(time)
}

fun getDifferenceDays(past: Date, feature: Date): Long {
    val diff = feature.time - past.time
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
}

fun parseStringToDate(
    string: String,
    sdf: SimpleDateFormat = SimpleDateFormat("dd-mm-yyyy", Locale.getDefault())
): Date {
    return try {
        sdf.parse(string) ?: Date()
    } catch (ex: ParseException) {
        Date()
    }
}
