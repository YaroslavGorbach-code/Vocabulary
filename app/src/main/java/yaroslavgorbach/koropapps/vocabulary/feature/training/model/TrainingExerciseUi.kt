package yaroslavgorbach.koropapps.vocabulary.feature.training.model

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToIconResMapper

class TrainingExerciseUi(private val trainingExerciseEntity: TrainingExerciseEntity) {

    val nameRes: Int
        get() = trainingExerciseEntity.name.id

    val progress: Int
        get() = trainingExerciseEntity.progress

    val iconRes: Int
        get() = ExerciseNameToIconResMapper().map(trainingExerciseEntity.name)

}