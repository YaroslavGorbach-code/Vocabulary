package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class StatisticsExerciseTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val exerciseNameRes: Int,
    val value: Float,
    val date: Date
)
