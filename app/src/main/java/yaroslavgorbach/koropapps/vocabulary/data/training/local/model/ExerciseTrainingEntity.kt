package yaroslavgorbach.koropapps.vocabulary.data.training.local.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

@Entity
data class ExerciseTrainingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: ExerciseName,
    val aim: Int,
    val performed: Int
) {
    val progress: Int
        get() = ((performed.toFloat() / aim.toFloat()) * 100).toInt()
}