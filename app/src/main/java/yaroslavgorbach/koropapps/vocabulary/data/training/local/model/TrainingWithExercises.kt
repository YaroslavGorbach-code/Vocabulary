package yaroslavgorbach.koropapps.vocabulary.data.training.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class TrainingWithExercises(
    @Embedded
    val trainingEntity: TrainingEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val exerciseEntities: List<ExerciseTrainingEntity>
) {
    val progress: Int
        get() {
            var p = 0
            exerciseEntities.forEach {
                p += it.progress
            }
            return (p.toFloat() / exerciseEntities.size.toFloat()).toInt()
        }
}
