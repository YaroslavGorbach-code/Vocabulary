package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.Training

data class TrainingUi(private val trainings: List<Training>) {
    val first: Training
        get() = trainings[0]

    val second: Training
        get() = trainings[1]

    val third: Training
        get() = trainings[2]

    val fourth: Training
        get() = trainings[3]

    val fifth: Training
        get() = trainings[4]
}
