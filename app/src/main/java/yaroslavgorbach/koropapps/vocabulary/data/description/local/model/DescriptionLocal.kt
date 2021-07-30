package yaroslavgorbach.koropapps.vocabulary.data.description.local.model

import androidx.annotation.StringRes
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

data class DescriptionLocal(private val exercise: Exercise, @StringRes private val textRes: Int) {

    val exerciseIconRes: Int
        get() = exercise.icon

    val exerciseName: ExerciseName
        get() = exercise.name

    val descriptionTextRes: Int
        get() = textRes

}
