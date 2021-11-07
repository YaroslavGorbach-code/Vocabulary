package yaroslavgorbach.koropapps.vocabulary.data.common

import androidx.room.TypeConverter
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

object ExerciseNameConverter {
    @TypeConverter
    fun toHealth(value: String) = enumValueOf<ExerciseName>(value)

    @TypeConverter
    fun fromHealth(value: ExerciseName) = value.name
}