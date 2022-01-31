package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class ExerciseNameToMaxLinesMapper {
    fun map(exerciseName: ExerciseName): Int {
        return when (exerciseName) {
            ExerciseName.SOUND_COMBINATIONS,
            ExerciseName.STORYTELLER_IMPROVISER -> 2
            ExerciseName.TONGUE_TWISTERS_EASY,
            ExerciseName.TONGUE_TWISTERS_HARD,
            ExerciseName.TONGUE_TWISTERS_VERY_HARD,
            ExerciseName.PROBLEM_SOLVING,
            ExerciseName.QUESTION_ANSWER -> Int.MAX_VALUE
            else -> 1
        }
    }
}