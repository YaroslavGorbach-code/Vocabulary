package yaroslavgorbach.koropapps.vocabulary.feature.training.model

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

class TrainingWithExercisesUi(private val trainingWithExercisesEntity: TrainingWithExercisesEntity) {

    val exercises: List<TrainingExerciseUi>
        get() = trainingWithExercisesEntity
            .exercises
            .map(::TrainingExerciseUi)
            .filter { it.isFinished.not() }

    val progress: Int
        get() = trainingWithExercisesEntity.progress

}