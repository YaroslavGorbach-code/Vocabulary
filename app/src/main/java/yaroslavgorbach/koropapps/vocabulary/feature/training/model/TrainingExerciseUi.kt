package yaroslavgorbach.koropapps.vocabulary.feature.training.model

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseCategoryMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToIconResMapper

class TrainingExerciseUi(private val trainingExerciseEntity: TrainingExerciseEntity) {

    val id: Long
        get() = trainingExerciseEntity.id

    val name: ExerciseName
        get() = trainingExerciseEntity.name

    val category: ExerciseCategory
        get() = ExerciseNameToExerciseCategoryMapper().map(trainingExerciseEntity.name)

    val progress: Int
        get() = trainingExerciseEntity.progress

    val iconRes: Int
        get() = ExerciseNameToIconResMapper().map(trainingExerciseEntity.name)

    val isFinished: Boolean
        get() = trainingExerciseEntity.isFinished

    val aim: Int
        get() = trainingExerciseEntity.aim

    val performed: Int
        get() = trainingExerciseEntity.performed

}