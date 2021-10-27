package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class StatisticsDailyTrainingTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var summaryTrainingTimeMc: Long,
    val date: Date
){
    val summaryTrainingTimeMinutes: Long
        get() = summaryTrainingTimeMc / 60000
}
