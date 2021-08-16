package yaroslavgorbach.koropapps.vocabulary.feature.training.model

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToIconResMapper

class TrainingExerciseUi(private val trainingExerciseEntity: TrainingExerciseEntity) {

    val nameRes: Int
        get() = trainingExerciseEntity.name.id

    val aim: Int
        get() = trainingExerciseEntity.aim

    val performed: Int
        get() = trainingExerciseEntity.performed

    val process: Int
        get() = trainingExerciseEntity.progress

    val iconRes: Int
        get() = ExerciseNameToIconResMapper().map(trainingExerciseEntity.name)

}