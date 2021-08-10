package yaroslavgorbach.koropapps.vocabulary.data.training.local.model
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

data class ExerciseTraining(
    val id: Long,
    val name: ExerciseName,
    val aim: Int,
    val performed: Int
) {
    val progress: Int
        get() = ((performed.toFloat() / aim.toFloat()) * 100).toInt()
}
