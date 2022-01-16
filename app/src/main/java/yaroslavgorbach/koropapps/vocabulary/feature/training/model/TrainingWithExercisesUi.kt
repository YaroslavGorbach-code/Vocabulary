package yaroslavgorbach.koropapps.vocabulary.feature.training.model

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

class TrainingWithExercisesUi(private val trainingWithExercisesEntity: TrainingWithExercisesEntity) {

    val exercises: List<TrainingExerciseUi>
        get() = trainingWithExercisesEntity
            .exercises
            .map(::TrainingExerciseUi)

    val progress: Int
        get() = trainingWithExercisesEntity.progress

    val daysWithoutInterruption: Int
        get() = trainingWithExercisesEntity.training.daysWithoutInterruption

    val isTrainingFinished: Boolean
        get() = trainingWithExercisesEntity.isFinished

}