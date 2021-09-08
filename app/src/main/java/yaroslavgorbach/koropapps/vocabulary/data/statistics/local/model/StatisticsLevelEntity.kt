package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StatisticsLevelEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
    var summaryTrainingTimeMc: Long,
    var exercisesCompleted: Int,
    var dailyTrainingsCompleted: Int,
) {
    val summaryTrainingTimeMinutes: Long
        get() = summaryTrainingTimeMc / 60000

}
