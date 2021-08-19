package yaroslavgorbach.koropapps.vocabulary.data.training.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

@Entity
data class TrainingExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val trainingId: Long,
    val name: ExerciseName,
    val aim: Int,
    var performed: Int = 0
) {
    val progress: Int
        get() = ((performed.toFloat() / aim.toFloat()) * 100).toInt()

    val isFinished: Boolean
        get() = performed >= aim
}
