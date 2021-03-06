package yaroslavgorbach.koropapps.vocabulary.feature.training.model

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity
import java.util.*

class TrainingWithExercisesUi(
    trainingWithExercisesEntity: TrainingWithExercisesEntity,
    previousTrainingWithExercisesEntity: TrainingWithExercisesEntity,
) {
    val exercises: List<TrainingExerciseUi> = trainingWithExercisesEntity
        .exercises
        .map(::TrainingExerciseUi)

    val progress: Int = trainingWithExercisesEntity.exercisesProgress

    val daysWithoutInterruption: Int = trainingWithExercisesEntity.training.daysWithoutInterruption

    val isTrainingFinished: Boolean = trainingWithExercisesEntity.areAllTrainingExercisesFinished

    val lastTrainingDate: Date? = previousTrainingWithExercisesEntity.training.date
}