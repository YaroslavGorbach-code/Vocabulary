package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import java.util.*

@Entity
data class StatisticsExerciseValueEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val exerciseName: ExerciseName,
    val value: Int,
    val date: Date
)
