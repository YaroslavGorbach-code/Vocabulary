package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation.factory

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercises

class TrainingFactory {
    fun create(): TrainingWithExercises {
        return TrainingWithExercises(TrainingEntity(0, null), emptyList())
    }
}