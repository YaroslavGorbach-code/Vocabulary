package yaroslavgorbach.koropapps.vocabulary.data.training.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class TrainingWithExercisesEntity(
    @Embedded
    val training: TrainingEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "trainingId"
    )
    val exercises: List<TrainingExerciseEntity>
) {
    val progress: Int
        get() {
            var p = 0
            exercises.forEach {
                p += it.progress
            }
            return (p.toFloat() / exercises.size.toFloat()).toInt()
        }
}
