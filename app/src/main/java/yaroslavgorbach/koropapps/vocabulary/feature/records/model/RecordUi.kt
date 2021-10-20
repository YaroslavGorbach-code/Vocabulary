package yaroslavgorbach.koropapps.vocabulary.feature.records.model

import android.text.format.DateUtils
import yaroslavgorbach.koropapps.vocabulary.R
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

data class RecordUi(val file: File, var recordState: RecordState = RecordState.Stop) {

    sealed class RecordState {
        object Playing : RecordState()
        object Pause : RecordState()
        object Stop : RecordState()
    }

    val lastModified: String
        get() = DateUtils.getRelativeTimeSpanString(file.lastModified()).toString()

    val name: String
        get() = file.name

    val duration: String
        get() = getFileDuration(file)

    val playIconRes: Int
        get() = if (recordState == RecordState.Playing) R.drawable.ic_pause_round else R.drawable.ic_play_round


    private fun getFileDuration(file: File): String {
        val duration = (file.absoluteFile.length() / 2f).toDouble()
        val durationL = duration.toLong()
        return String.format(
            Locale.US, "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(durationL),
            TimeUnit.MILLISECONDS.toMinutes(durationL) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(durationL) % TimeUnit.MINUTES.toSeconds(1)
        )
    }
}