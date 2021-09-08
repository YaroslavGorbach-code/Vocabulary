package yaroslavgorbach.koropapps.vocabulary.feature.common.factory

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsLevelEntity
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import java.util.*

class StatisticsEntityFactory {

    fun createValueEntity(exerciseName: ExerciseName, value: Int): StatisticsExerciseValueEntity {
        return StatisticsExerciseValueEntity(
            exerciseNameRes = exerciseName.id,
            value = value,
            date = Date()
        )
    }

    fun createTimeEntity(exerciseName: ExerciseName, value: Float): StatisticsExerciseTimeEntity {
        return StatisticsExerciseTimeEntity(
            exerciseNameRes = exerciseName.id,
            value = value,
            date = Date()
        )
    }

    fun createDayEntity(timeSpentOnExercise: Long): StatisticsDailyTrainingTimeEntity {
        return StatisticsDailyTrainingTimeEntity(
            summaryTrainingTime = timeSpentOnExercise,
            date = Date()
        )
    }

    fun createLevelEntity(
        summaryTrainingTime: Long,
        exerciseType: ExerciseType,
    ): StatisticsLevelEntity {
        return StatisticsLevelEntity(
            summaryTrainingTimeMc = summaryTrainingTime,
            exercisesCompleted = 1,
            dailyTrainingsCompleted = if (exerciseType is ExerciseType.Common) 0 else 1
        )
    }

}