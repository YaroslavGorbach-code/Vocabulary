package yaroslavgorbach.koropapps.vocabulary.data.training.local.model

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation

data class TrainingWithExercisesEntity(
    @Embedded
    val training: TrainingEntity,

    @Relation(parentColumn = "id", entityColumn = "trainingId")
    val exercises: List<TrainingExerciseEntity>
) {

    val exercisesProgress: Int
        get() {
            val process = exercises
                .map(TrainingExerciseEntity::progress)
                .reduceOrNull(Int::plus)?.toFloat() ?: 0f
            return (process / exercises.size.toFloat()).toInt()
        }

    @Ignore
    val areAllTrainingExercisesFinished = exercisesProgress == 100
}
