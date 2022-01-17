package yaroslavgorbach.koropapps.vocabulary.data.training.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class TrainingWithExercisesEntity(
    @Embedded
    val training: TrainingEntity,

    @Relation(parentColumn = "id", entityColumn = "trainingId")
    val exercises: List<TrainingExerciseEntity>
) {
    val progress: Int
        get() {
//            var p = 0
//            exercises.forEach {
//                p += it.progress
//            }

           val p = exercises.map(TrainingExerciseEntity::progress).reduceOrNull { val1, val2 ->
                val1 + val2
            }

            return ((p?.toFloat() ?: 0f) / exercises.size.toFloat()).toInt()
        }

    val isFinished: Boolean
        get() = progress == 100
}
