package yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model

import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseCategoryMapper
import yaroslavgorbach.koropapps.vocabulary.utils.getDifferenceDays
import java.util.*

data class Exercise(
    val name: ExerciseName,
    val category: ExerciseCategory = ExerciseNameToExerciseCategoryMapper().map(name),
    var isFavorite: Boolean,
    val createDate: Date = Date(1)
) {
    val isNew = getDifferenceDays(createDate, Date()) < 30
}
