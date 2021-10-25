package yaroslavgorbach.koropapps.vocabulary.business.statistics.factory

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
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
}