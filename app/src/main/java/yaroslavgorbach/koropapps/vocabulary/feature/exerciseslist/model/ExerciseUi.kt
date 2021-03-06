package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToIconResMapper

data class ExerciseUi(private val exercise: Exercise) {

    val name: ExerciseName
        get() = exercise.name

    val nameRes: Int
        get() = exercise.name.id

    val iconRes: Int
        get() = ExerciseNameToIconResMapper().map(exercise.name)

    val isFavorite: Boolean
        get() = exercise.isFavorite

    val category: ExerciseCategory
        get() = exercise.category

    val isNew: Boolean
        get() = exercise.isNew
}