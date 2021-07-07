package yaroslavgorbach.koropapps.vocabulary.data.description.local.model

import androidx.annotation.StringRes
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

data class Description(val exerciseName: ExerciseName, @StringRes val text: Int)
