package yaroslavgorbach.koropapps.vocabulary.feature.exercise.common.factory

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity
import java.util.*

class StatisticsEntityFactory {
    fun create(exerciseName: ExerciseName, value: Int): StatisticsEntity {
        return StatisticsEntity(
            exerciseNameRes = exerciseName.id,
            value = value,
            date = Date()
        )
    }
}