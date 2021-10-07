package yaroslavgorbach.koropapps.vocabulary.data.exercises.local

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode

interface ExercisesDataStore {
    fun observe(context: Context): Flow<List<Exercise>>

    suspend fun changeFavorite(exerciseName: ExerciseName, context: Context)

    fun get(context: Context, exerciseName: ExerciseName): Flow<Exercise>
}