package yaroslavgorbach.koropapps.vocabulary.feature.common.factory

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity
import java.util.*

class StatisticsEntityFactory {

    fun createValueEntity(exerciseName: ExerciseName, value: Int): StatisticsValueEntity {
        return StatisticsValueEntity(
            exerciseNameRes = exerciseName.id,
            value = value,
            date = Date()
        )
    }

    fun createTimeEntity(exerciseName: ExerciseName, value: Float): StatisticsTimeEntity {
        return StatisticsTimeEntity(
            exerciseNameRes = exerciseName.id,
            value = value,
            date = Date()
        )
    }
}