package yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.model

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import java.io.Serializable

// TODO: 8/18/2021 replace serializable on parcelable
sealed class ExerciseType : Serializable {
    class Common(val name: ExerciseName) : ExerciseType()
    class Training(val name: ExerciseName, val exerciseId: Long) : ExerciseType()

    fun getExerciseName(): ExerciseName {
        return when (this) {
            is Common -> this.name
            is Training -> this.name
        }
    }
}