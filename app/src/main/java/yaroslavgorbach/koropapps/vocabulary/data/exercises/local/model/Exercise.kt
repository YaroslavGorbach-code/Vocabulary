package yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model

import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseCategoryMapper

data class Exercise(
    val name: ExerciseName,
    val category: ExerciseCategory = ExerciseNameToExerciseCategoryMapper().map(name),
    var isFavorite: Boolean
)
