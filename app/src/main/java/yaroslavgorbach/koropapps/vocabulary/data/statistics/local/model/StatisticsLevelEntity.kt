package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StatisticsLevelEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
    val summaryTrainingTime: Int,
    val exercisesCompleted: Int,
    val dailyTrainingsCompleted: Int,
)
