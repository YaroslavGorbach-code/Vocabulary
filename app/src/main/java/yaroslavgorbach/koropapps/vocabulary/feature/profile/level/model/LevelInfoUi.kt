package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model

import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsLevelEntity
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation.mapper.MapSummaryTrainingTimeToLevel

data class LevelInfoUi(private val levelEntity: StatisticsLevelEntity) {

    val level: Level
        get() = MapSummaryTrainingTimeToLevel().map(levelEntity.summaryTrainingTime)

    val progress: Int
        get() = try {
            ((levelEntity.summaryTrainingTime.toFloat() / level.trainingTimeReared.toFloat()) * 100).toInt()
        } catch (ex: Throwable) {
            0
        }

    val summaryTrainingTime: String
        get() = levelEntity.summaryTrainingTime.toString()

    val completedExercises: String
        get() = levelEntity.exercisesCompleted.toString()

    val completedDailyTrainings: String
        get() = levelEntity.dailyTrainingsCompleted.toString()
}
