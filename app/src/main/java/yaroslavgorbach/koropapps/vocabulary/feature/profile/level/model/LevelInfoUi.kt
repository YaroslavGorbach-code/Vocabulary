package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsLevelEntity
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation.mapper.MapSummaryTrainingTimeToLevel

data class LevelInfoUi(private val levelEntity: StatisticsLevelEntity) {

    private val progress: Int
        get() = try {
            (((levelEntity.summaryTrainingTimeMinutes.toFloat() - level.levelTrainingTimeRequired.toFloat()) / level.nextLevelTrainingTimeRequired.toFloat()) * 100f).toInt()
        } catch (ex: Throwable) {
            0
        }

    val progressIconRes: Int
        get() = when (progress) {
            in 0..10 -> R.drawable.ic_level_10
            in 10..20 -> R.drawable.ic_level_20
            in 20..30 -> R.drawable.ic_level_30
            in 30..40 -> R.drawable.ic_level_40
            in 40..50 -> R.drawable.ic_level_50
            in 50..60 -> R.drawable.ic_level_60
            in 60..70 -> R.drawable.ic_level_70
            in 70..80 -> R.drawable.ic_level_80
            in 80..100 -> R.drawable.ic_level_90
            else -> R.drawable.ic_level_10
        }

    val level: Level
        get() = MapSummaryTrainingTimeToLevel().map(levelEntity.summaryTrainingTimeMinutes)

    val summaryTrainingTime: String
        get() = levelEntity.summaryTrainingTimeMinutes.toString()

    val completedExercises: String
        get() = levelEntity.exercisesCompleted.toString()

    val completedDailyTrainings: String
        get() = levelEntity.dailyTrainingsCompleted.toString()
}
