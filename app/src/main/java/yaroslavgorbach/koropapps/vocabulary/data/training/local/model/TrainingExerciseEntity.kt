package yaroslavgorbach.koropapps.vocabulary.data.training.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseCategoryMapper

@Entity
data class TrainingExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val trainingId: Long = 0,
    val name: ExerciseName = ExerciseName.NARRATOR_VERBS,
    val aim: Int = 0,
    var performed: Int = 0
) {
    val progress: Int
        get() = ((performed.toFloat() / aim.toFloat()) * 100).toInt()

    val isFinished: Boolean
        get() = performed >= aim

    val category: ExerciseCategory
        get() = ExerciseNameToExerciseCategoryMapper().map(exerciseName = name)
}
