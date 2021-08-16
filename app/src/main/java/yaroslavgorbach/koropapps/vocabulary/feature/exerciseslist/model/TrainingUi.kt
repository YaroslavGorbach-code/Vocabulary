package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity


data class TrainingUi(private val trainingWithExerciseEntities: List<TrainingWithExercisesEntity>) {
    val first: TrainingWithExercisesEntity?
        get() = trainingWithExerciseEntities.getOrNull(0)

    val second: TrainingWithExercisesEntity?
        get() = trainingWithExerciseEntities.getOrNull(1)

    val third: TrainingWithExercisesEntity?
        get() = trainingWithExerciseEntities.getOrNull(2)

    val fourth: TrainingWithExercisesEntity?
        get() = trainingWithExerciseEntities.getOrNull(3)

    val fifth: TrainingWithExercisesEntity?
        get() = trainingWithExerciseEntities.getOrNull(4)
}
