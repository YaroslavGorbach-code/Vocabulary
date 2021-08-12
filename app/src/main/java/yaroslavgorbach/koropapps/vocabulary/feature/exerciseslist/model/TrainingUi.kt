package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercises


data class TrainingUi(private val trainingWithExercises: List<TrainingWithExercises>) {
    val first: TrainingWithExercises
        get() = trainingWithExercises[0]

    val second: TrainingWithExercises
        get() = trainingWithExercises[1]

    val third: TrainingWithExercises
        get() = trainingWithExercises[2]

    val fourth: TrainingWithExercises
        get() = trainingWithExercises[3]

    val fifth: TrainingWithExercises
        get() = trainingWithExercises[4]
}
